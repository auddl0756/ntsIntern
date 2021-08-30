package com.nts.intern.reserve.repository.reviewWrite;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.reserve.config.ApplicationConfig;
import com.nts.intern.reserve.dto.reviewWrite.ReviewSaveDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ReviewWriteRepositoryTest {
	@Autowired
	private ReviewWriteRepository reviewWriteRepository;

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	@Test
	public void notNullTest() {
		assertThat(reviewWriteRepository).isNotNull();
	}

	@Test
	public void findByIdTest() {
		int sampleReservationInfoId = 1;
		System.out.println(reviewWriteRepository.findById(sampleReservationInfoId));
	}

	@Test
	@Transactional
	public void saveTest() {
		ReviewSaveDto sampleDto = ReviewSaveDto.builder()
			.reservationInfoId(2)
			.productId(2)
			.score(3)
			.comment("good~~~")
			.fileName("sampleFileName")
			.saveFileName("sample/sampleFileName")
			.contentType("image")
			.deleteFlag(false)
			.createDate(FORMATTER.format(LocalDateTime.now()))
			.modifyDate(FORMATTER.format(LocalDateTime.now()))
			.build();

		reviewWriteRepository.save(sampleDto);
	}
}
