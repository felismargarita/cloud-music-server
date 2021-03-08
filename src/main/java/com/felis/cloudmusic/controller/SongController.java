package com.felis.cloudmusic.controller;

import cn.hutool.Hutool;
import cn.hutool.core.io.FileUtil;
import com.felis.cloudmusic.entity.Album;
import com.felis.cloudmusic.entity.Singer;
import com.felis.cloudmusic.entity.Song;
import com.felis.cloudmusic.service.IAlbumService;
import com.felis.cloudmusic.service.ISingerService;
import com.felis.cloudmusic.service.ISongService;
import com.felis.cloudmusic.service.impl.AlbumService;
import com.felis.cloudmusic.utils.SongUtil;
import lombok.extern.log4j.Log4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/song")
@Log4j
public class SongController {

    @Value("${resource.song.path}")
    private String songPath;

    @Value("${picture.path}")
    private String picturePath;

    @Autowired
    private ISingerService singerService;

    @Autowired
    private IAlbumService albumService;

    @Autowired
    private ISongService songService;

    @Configuration
    public class Config implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/picture/**").addResourceLocations(picturePath);
            registry.addResourceHandler("/song/**").addResourceLocations(songPath);
        }
    }

    @PostMapping
    public void upload(@RequestBody MultipartFile multipartFile) throws IOException {
        byte[] hashSong = DigestUtils.md5Digest(multipartFile.getBytes());
        String hashHexSong = DigestUtils.md5DigestAsHex(hashSong);
        File fileOnFs = new File(songPath+hashHexSong);
        if(!fileOnFs.exists()){
            multipartFile.transferTo(fileOnFs);
        }//歌曲写到文件系统
        Song song = SongUtil.getMusicInfo(fileOnFs);
        song.setSongUrl("/song/"+hashHexSong);
        //图片写到文件系统
        byte[] hashPicture = DigestUtils.md5Digest(song.getPicture());
        String hashHexPicture = DigestUtils.md5DigestAsHex(hashPicture);
        File pictureOnFs = new File(picturePath+hashHexPicture);
        if(!pictureOnFs.exists()){
            FileUtil.writeBytes(song.getPicture(),pictureOnFs);
        }
        song.setPictureUrl("/picture/"+hashHexPicture);
        Singer singer = singerService.mergeSingerByName(song.getSinger());
        song.setSingerId(singer.getId());
        Album album = albumService.mergeAlbumByName(song.getAlbum());
        song.setAlbumId(album.getId());

    }
}
