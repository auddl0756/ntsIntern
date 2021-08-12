package com.nts.intern.reserve.service.detail;

import java.util.List;
import java.util.stream.Collectors;

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

	public double findAverageScore(int displayInfoId) {
		List<CommentDto> comments = commentRepository.findAllById(displayInfoId);
		int scoreSum = comments.stream()
			.mapToInt(CommentDto::getScore)
			.sum();

		return (double)Math.round((double)scoreSum * 10 / comments.size()) / 10;
	}

	public List<CommentDto> findAllCommentsById(int displayInfoId) {
		List<CommentDto> comments = commentRepository.findAllById(displayInfoId);

		for (CommentDto comment : comments) {
			comment.setCommentImages(commentImageRepository.findAllById(comment.getCommentId()));
		}

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
}
