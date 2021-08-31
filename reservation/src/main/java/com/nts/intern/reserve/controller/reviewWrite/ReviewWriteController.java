package com.nts.intern.reserve.controller.reviewWrite;

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
import com.nts.intern.reserve.service.util.FileLoadService;

@Controller
public class ReviewWriteController {
	private static final String uploadedFileUrl = "C:/tmp/";
	private static final int ONE_KB = 1024;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

	@Autowired
	private ReviewWriteService reviewWriteService;

	@Autowired
	FileLoadService fileLoadService;

	@GetMapping("/reviewWrite/{reservationInfoId}")
	public String reviewWritePage(@PathVariable int reservationInfoId) {
		return "reviewWrite";
	}

	@PostMapping("/api/reservations/{reservationInfoId}/comments")
	public String makeComment(@PathVariable int reservationInfoId,
		@RequestParam("form_comment") String comment,
		@RequestParam("form_productId") int productId,
		@RequestParam("form_score") double score,
		@RequestParam("file") MultipartFile file,
		@RequestParam("form_email") String email) {

		fileLoadService.saveFile(file, uploadedFileUrl);

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
