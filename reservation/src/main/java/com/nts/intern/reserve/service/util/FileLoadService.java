package com.nts.intern.reserve.service.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nts.intern.reserve.dto.util.FileDownloadDto;
import com.nts.intern.reserve.repository.util.FileLoadRepository;

@Service
public class FileLoadService {
	@Autowired
	private FileLoadRepository fileLoadRepository;

	private static final int ONE_KB = 1024;

	public void saveFile(MultipartFile file, String uploadedFileUrl) {
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
	}

	public FileDownloadDto getCommentFileInfo(int commentId) {
		return fileLoadRepository.findImgByCommentId(commentId);
	}

	public FileDownloadDto getFileInfo(int displayInfoId) {
		return fileLoadRepository.findImgByDisplayId(displayInfoId);
	}

	public FileDownloadDto getThumbnailFileInfo(int productId) {
		return fileLoadRepository.findImgByProductId(productId);
	}

	public FileDownloadDto getUploadedFileInfo(int commentImageId) {
		return fileLoadRepository.findUploadedImgById(commentImageId);
	}
}
