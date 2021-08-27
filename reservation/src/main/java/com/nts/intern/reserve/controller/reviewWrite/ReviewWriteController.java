package com.nts.intern.reserve.controller.reviewWrite;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nts.intern.reserve.dto.reviewWrite.ReviewSaveDto;
import com.nts.intern.reserve.service.reviewWrite.ReviewWriteService;

@Controller
public class ReviewWriteController {
	private static final String uploadedFileUrl = "C:/tmp/";
	private static final int ONE_KB = 1024;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	@Autowired
	private ReviewWriteService reviewWriteService;

	@GetMapping("/reviewWrite/{reservationInfoId}")
	public String reviewWritePage(@PathVariable int reservationInfoId) {
		return "reviewWrite";
	}

	@PostMapping("/api/reservations/{reservationInfoId}/comments")
	public String makeComment(@PathVariable int reservationInfoId,
		@RequestParam("form_comment") String comment,
		@RequestParam("form_productId") int productId,
		@RequestParam("form_score") int score,
		@RequestParam("file") MultipartFile file,
		@RequestParam("form_email") String email) {

		try (
			FileOutputStream fileOutputStream = new FileOutputStream(uploadedFileUrl + file.getOriginalFilename());
			InputStream inputStream = file.getInputStream();) {

			byte[] buffer = new byte[ONE_KB];
			for (int readCount = 0; readCount != -1; readCount = inputStream.read(buffer)) {
				fileOutputStream.write(buffer, 0, readCount);
			}
		} catch (IOException iOException) {
			iOException.printStackTrace();
		}

		ReviewSaveDto dto = ReviewSaveDto.builder()
			.reservationInfoId(reservationInfoId)
			.comment(comment)
			.productId(productId)
			.score(score)
			.fileName(file.getOriginalFilename())
			.saveFileName(uploadedFileUrl + file.getOriginalFilename())
			.contentType(file.getContentType())
			.deleteFlag(false)
			.createDate(FORMATTER.format(LocalDateTime.now()))
			.modifyDate(FORMATTER.format(LocalDateTime.now()))
			.build();

		reviewWriteService.saveComment(dto);

		return "redirect:/reservations?resrv_email=" + email;
	}
}
