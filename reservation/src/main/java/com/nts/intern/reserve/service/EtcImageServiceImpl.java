package com.nts.intern.reserve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.EtcImageDto;
import com.nts.intern.reserve.repository.EtcImageRepository;

@Service
public class EtcImageServiceImpl implements EtcImageService {
	@Autowired
	private EtcImageRepository etcImageRepository;

	@Override
	public List<EtcImageDto> findAllEtcImagesById(int displayInfoId) {
		return etcImageRepository.findById(displayInfoId);
	}
}
