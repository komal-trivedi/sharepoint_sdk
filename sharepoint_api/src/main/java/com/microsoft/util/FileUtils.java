package com.microsoft.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;

@Slf4j
public class FileUtils {

	public static boolean writeResponseBodyToDisk(ResponseBody body, String fileName) {
		try {
			// todo change the file location/name according to your needs
			File futureStudioIconFile = new File(
					"D:\\Crypto\\ContentMap Docs\\Download" + File.separator + fileName);

			InputStream inputStream = null;
			OutputStream outputStream = null;

			try {
				byte[] fileReader = new byte[4096];

				long fileSize = body.contentLength();
				long fileSizeDownloaded = 0;

				inputStream = body.byteStream();
				outputStream = new FileOutputStream(futureStudioIconFile);

				while (true) {
					int read = inputStream.read(fileReader);

					if (read == -1) {
						break;
					}

					outputStream.write(fileReader, 0, read);

					fileSizeDownloaded += read;

					log.info("file download: " + fileSizeDownloaded + " of " + fileSize);
				}

				outputStream.flush();

				return true;
			} catch (IOException e) {
				return false;
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			}
		} catch (IOException e) {
			return false;
		}
	}
	
	public static InputStream getFileInputStream(ResponseBody body) {
		return body.byteStream();
	}
	
	public static boolean writeResponseBodyToDisk(InputStream inputStream, long fileSize, String fileName) {
		try {
			// todo change the file location/name according to your needs
			File futureStudioIconFile = new File(
					"D:\\Crypto\\ContentMap Docs\\Download" + File.separator + fileName);

			OutputStream outputStream = null;

			try {
				byte[] fileReader = new byte[4096];

				long fileSizeDownloaded = 0;

				outputStream = new FileOutputStream(futureStudioIconFile);

				while (true) {
					int read = inputStream.read(fileReader);

					if (read == -1) {
						break;
					}

					outputStream.write(fileReader, 0, read);

					fileSizeDownloaded += read;

					log.info("file download: " + fileSizeDownloaded + " of " + fileSize);
				}

				outputStream.flush();

				return true;
			} catch (IOException e) {
				return false;
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			}
		} catch (IOException e) {
			return false;
		}
	}

}
