package com.nts.intern.reserve.service;

import java.util.List;

import com.nts.intern.reserve.dto.EtcImageDto;

public interface EtcImageService {
	List<EtcImageDto> findAllEtcImagesById(int displayInfoId);
}
