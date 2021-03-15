package com.felis.cloudmusic;

import com.felis.cloudmusic.entity.Song;
import com.felis.cloudmusic.utils.SongUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudmusicApplicationTests {

	@Test
	void contextLoads() {
		Song song = SongUtil.getMusicInfo("D:\\music\\song\\betterman.mp3");
		System.out.println(song.getName());
		System.out.println(song.getSinger());
		System.out.println(song.getAlbum());
		System.out.println(song.getDuration());
	}

}
