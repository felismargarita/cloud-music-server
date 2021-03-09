package com.felis.cloudmusic.controller;

import cn.hutool.core.io.FileUtil;
import com.felis.cloudmusic.entity.*;
import com.felis.cloudmusic.service.IAlbumService;
import com.felis.cloudmusic.service.ISingerService;
import com.felis.cloudmusic.service.ISongService;
import com.felis.cloudmusic.utils.CommonResUtil;
import com.felis.cloudmusic.utils.SongUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/song")
@Log4j
public class SongController {

    @Value("${resource.song.path}")
    private String songPath;

    @Value("${resource.picture.path}")
    private String picturePath;

    @Autowired
    private ISingerService singerService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private ISongService songService;

    @PostMapping("/paging")
    public ResResult getPaging(@RequestBody PageParam<Song> pageParam){
        return CommonResUtil.success(songService.getPaging(pageParam));
    }

    @GetMapping("/all")
    public ResResult getAll(){
        return CommonResUtil.success(songService.getAll());
    }

    @PostMapping("/upload")
    public ResResult upload(MultipartFile file) throws IOException {
        byte[] hashSong = DigestUtils.md5Digest(file.getBytes());
        String hashHexSong = DigestUtils.md5DigestAsHex(hashSong);
        File fileOnFs = new File(songPath+hashHexSong+".mp3");
        if(!fileOnFs.exists()){
            file.transferTo(fileOnFs);
        }//歌曲写到文件系统

        Song song = SongUtil.getMusicInfo(fileOnFs);
        song.setSongUrl("/resource/song/"+hashHexSong+".mp3");

        if(song.getPicture() !=null){
            byte[] hashPicture = DigestUtils.md5Digest(song.getPicture());
            String hashHexPicture = DigestUtils.md5DigestAsHex(hashPicture);
            File pictureOnFs = new File(picturePath+hashHexPicture);
            if(!pictureOnFs.exists()){
                FileUtil.writeBytes(song.getPicture(),pictureOnFs);
            }//图片写到文件系统
            song.setPictureUrl("/resource/picture/"+hashHexPicture);
        }

        Singer singer = singerService.mergeSingerByName(song.getSinger());
        song.setSingerId(singer.getId());
        Album album = albumService.mergeAlbumByName(song.getAlbum());
        song.setAlbumId(album.getId());
        songService.addSong(song);
        log.info("上传歌曲:"+song.toString());
        return CommonResUtil.success();
    }
}
