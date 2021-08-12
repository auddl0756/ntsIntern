package com.nts.intern.reserve.service.detail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.reserve.dto.detail.EtcImageDto;
import com.nts.intern.reserve.repository.detail.EtcImageRepository;

@Service
public class EtcImageService {
	@Autowired
	private EtcImageRepository etcImageRepository;

	public List<EtcImageDto> findAllEtcImagesById(int displayInfoId) {
		return etcImageRepository.findById(displayInfoId);
	}
}
