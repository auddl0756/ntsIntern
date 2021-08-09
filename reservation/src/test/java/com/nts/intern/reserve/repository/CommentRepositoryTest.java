package com.nts.intern.reserve.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommentRepositoryTest {
	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void findByIdTest() {
		int sampleCommentId = 1;
		System.out.println(commentRepository.findAllById(sampleCommentId));
	}

}
