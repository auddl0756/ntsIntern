package com.nts.intern.reserve.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.CommentDto;
import com.nts.intern.reserve.dto.DisplayInfoDto;
import com.nts.intern.reserve.dto.DisplayInfoImageDto;
import com.nts.intern.reserve.dto.ProductImageDto;
import com.nts.intern.reserve.dto.ProductPriceDto;
import com.nts.intern.reserve.repository.CommentImageRepository;
import com.nts.intern.reserve.repository.CommentRepository;
import com.nts.intern.reserve.repository.DisplayInfoImageRepository;
import com.nts.intern.reserve.repository.DisplayInfoRepository;
import com.nts.intern.reserve.repository.ProductImageRepository;
import com.nts.intern.reserve.repository.ProductPriceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductDetailServiceImpl implements ProductDetailService {
	private final CommentRepository commentRepository;
	private final CommentImageRepository commentImageRepository;
	private final DisplayInfoRepository displayInfoRepository;
	private final DisplayInfoImageRepository displayInfoImageRepository;
	private final ProductPriceRepository productPriceRepository;
	private final ProductImageRepository productImageRepository;

	@Override
	public double findAverageScore(int displayInfoId) {
		List<CommentDto> comments = commentRepository.findAllById(displayInfoId);
		int scoreSum = comments.stream()
			.mapToInt(CommentDto::getScore)
			.sum();

		return (double)Math.round((double)scoreSum * 10 / comments.size()) / 10;
	}

	@Override
	public List<CommentDto> findAllCommentsById(int displayInfoId) {
		List<CommentDto> comments = commentRepository.findAllById(displayInfoId);

		for (CommentDto comment : comments) {
			comment.setCommentImages(commentImageRepository.findAllById(comment.getCommentId()));
		}

		return comments;
	}

	@Override
	public DisplayInfoDto findDisplayInfoById(int displayInfoId) {
		return displayInfoRepository.findById(displayInfoId);
	}

	@Override
	public DisplayInfoImageDto findDisplayInfoImageById(int displayInfoId) {
		return displayInfoImageRepository.findById(displayInfoId);
	}

	@Override
	public List<ProductPriceDto> findAllPricesById(int displayInfoId) {
		return productPriceRepository.findById(displayInfoId);
	}

	@Override
	public List<ProductImageDto> findAllProductImagesById(int displayInfoId) {
		return productImageRepository.findById(displayInfoId);
	}
}
