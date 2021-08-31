package com.nts.intern.reserve.controller.fileLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.intern.reserve.dto.util.FileDownloadDto;
import com.nts.intern.reserve.service.util.FileLoadService;

@Controller
public class FileLoadController {
	@Autowired
	private FileLoadService fileLoadService;

	@GetMapping("/downloadByFileName")
	public void downloadByFileName(HttpServletResponse response, @RequestParam String saveFileName) {
		File file = new File(saveFileName);

		long fileLength = file.length();

		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", "image/png");
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	@GetMapping("/download/{displayInfoId}")
	public void download(HttpServletResponse response, @PathVariable int displayInfoId) {

		FileDownloadDto dto = fileLoadService.getFileInfo(displayInfoId);

		String fileName = dto.getFileName();
		String saveFileName = dto.getSaveFileName();
		String contentType = dto.getContentType();

		File file = new File(saveFileName);

		long fileLength = file.length();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	@GetMapping("/downloadCommentImage/{commentId}")
	public void downloadCommentImage(HttpServletResponse response, @PathVariable int commentId) {

		FileDownloadDto dto = fileLoadService.getFileInfo(commentId);

		String fileName = dto.getFileName();
		String saveFileName = dto.getSaveFileName();
		String contentType = dto.getContentType();

		File file = new File(saveFileName);

		long fileLength = file.length();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	@GetMapping("/downloadThumbnail/{productId}")
	public void downloadThumbnail(HttpServletResponse response, @PathVariable int productId) {

		FileDownloadDto dto = fileLoadService.getThumbnailFileInfo(productId);

		String fileName = dto.getFileName();
		String saveFileName = dto.getSaveFileName();
		String contentType = dto.getContentType();

		File file = new File(saveFileName);

		long fileLength = file.length();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}

	@GetMapping("/downloadUploaded/{commentImageId}")
	public void downloadUploadedImage(HttpServletResponse response, int commentImageId) {

		FileDownloadDto dto = fileLoadService.getUploadedFileInfo(commentImageId);

		String fileName = dto.getFileName();
		String saveFileName = dto.getSaveFileName();
		String contentType = dto.getContentType();

		File file = new File(saveFileName);

		long fileLength = file.length();

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", contentType);
		response.setHeader("Content-Length", "" + fileLength);
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		try (
			FileInputStream fis = new FileInputStream(saveFileName);
			OutputStream out = response.getOutputStream();) {

			int readCount = 0;
			byte[] buffer = new byte[1024];

			while ((readCount = fis.read(buffer)) != -1) {
				out.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error");
		}
	}
}
