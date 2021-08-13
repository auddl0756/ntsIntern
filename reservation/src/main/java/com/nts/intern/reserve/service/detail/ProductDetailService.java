package com.nts.intern.reserve.service.detail;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.detail.CommentDto;
import com.nts.intern.reserve.dto.detail.DisplayInfoDto;
import com.nts.intern.reserve.dto.detail.DisplayInfoImageDto;
import com.nts.intern.reserve.dto.detail.ProductImageDto;
import com.nts.intern.reserve.dto.detail.ProductPriceDto;
import com.nts.intern.reserve.repository.detail.CommentImageRepository;
import com.nts.intern.reserve.repository.detail.CommentRepository;
import com.nts.intern.reserve.repository.detail.DisplayInfoImageRepository;
import com.nts.intern.reserve.repository.detail.DisplayInfoRepository;
import com.nts.intern.reserve.repository.detail.ProductImageRepository;
import com.nts.intern.reserve.repository.detail.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductDetailService {
	private final CommentRepository commentRepository;
	private final CommentImageRepository commentImageRepository;
	private final DisplayInfoRepository displayInfoRepository;
	private final DisplayInfoImageRepository displayInfoImageRepository;
	private final ProductPriceRepository productPriceRepository;
	private final ProductImageRepository productImageRepository;

	public double findTotalAverageScore(int displayInfoId) {
		return commentRepository.findAverageById(displayInfoId);
	}

	public List<CommentDto> findAllCommentsById(int displayInfoId) {
		List<CommentDto> comments = commentRepository.findAllById(displayInfoId);

		preprocessComments(comments);

		return comments;
	}

	public List<CommentDto> findInitialCommentsById(int displayInfoId) {
		List<CommentDto> comments = commentRepository.findByIdLimit(displayInfoId);

		preprocessComments(comments);

		return comments;
	}

	public DisplayInfoDto findDisplayInfoById(int displayInfoId) {
		return displayInfoRepository.findById(displayInfoId);
	}

	public DisplayInfoImageDto findDisplayInfoImageById(int displayInfoId) {
		return displayInfoImageRepository.findById(displayInfoId);
	}

	public List<ProductPriceDto> findAllPricesById(int displayInfoId) {
		return productPriceRepository.findById(displayInfoId);
	}

	public List<ProductImageDto> findAllProductImagesById(int displayInfoId) {
		return productImageRepository.findById(displayInfoId);
	}

	public void preprocessComments(List<CommentDto> comments) {
		for (CommentDto comment : comments) {
			comment.setCommentImages(commentImageRepository.findAllById(comment.getCommentId()));
			if (comment.getReservationEmail().length() < 4) {
				comment.setReservationEmail("****");
			} else {
				comment.setReservationEmail(comment.getReservationEmail().substring(0, 4) + "****");
			}
		}
	}
}
