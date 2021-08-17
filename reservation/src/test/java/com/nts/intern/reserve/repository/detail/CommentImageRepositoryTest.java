package com.nts.intern.reserve.repository.detail;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.repository.detail.CommentImageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommentImageRepositoryTest {
	@Autowired
	private CommentImageRepository commentImageRepository;

	@Test
	public void findByIdTest() {
		IntStream.rangeClosed(1, 10).forEach(id -> {
			System.out.println(commentImageRepository.findAllById(id));
		});
	}

}
