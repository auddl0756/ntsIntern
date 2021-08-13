package com.nts.intern.reserve.repository.detail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.repository.detail.CommentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommentRepositoryTest {
	@Autowired
	private CommentRepository commentRepository;

	@Test
	public void findByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(commentRepository.findAllById(sampleDisplayInfoId));
	}

	@Test
	public void findByIdLimitTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(commentRepository.findByIdLimit(sampleDisplayInfoId));
	}

	@Test
	public void findAverageByIdTest() {
		int sampleDisplayInfoId = 1;
		System.out.println(commentRepository.findAverageById(sampleDisplayInfoId));
	}
}
