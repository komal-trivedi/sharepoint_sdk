package com.microsoft.client.sharepoint;

import java.io.IOException;
import java.io.InputStream;

import com.microsoft.dto.sharepoint.FilesAndFolderResponse;
import com.microsoft.dto.sharepoint.SitesResponse;
import com.microsoft.service.ServiceFactory;
import com.microsoft.service.SharepointService;
import com.microsoft.util.FileUtils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
public class SharepointClient {

	public SitesResponse getAllSites(String search) throws IOException {
		SharepointService service = ServiceFactory.createService(SharepointService.class);
		Call<SitesResponse> call = service.getAllSites(search);
		log.info("Url: {}", call.request().url().toString());
		Response<SitesResponse> response = call.execute();
		SitesResponse sitesResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			sitesResponse = response.body();
			log.info("Response: {}", sitesResponse);
		}
		return sitesResponse;

	}

	public FilesAndFolderResponse getFilesAndFolder(String path) throws IOException {
		SharepointService service = ServiceFactory.createService(SharepointService.class);
		Call<FilesAndFolderResponse> call = service.getFilesAndFolders(path);
		log.info("Url: {}", call.request().url().toString());
		Response<FilesAndFolderResponse> response = call.execute();
		FilesAndFolderResponse filesAndFolderResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			filesAndFolderResponse = response.body();
			log.info("Response: {}", filesAndFolderResponse);
		}
		return filesAndFolderResponse;

	}

	public FilesAndFolderResponse getListofFilesByFolder(String siteId, String folderId) throws IOException {
		SharepointService service = ServiceFactory.createService(SharepointService.class);
		Call<FilesAndFolderResponse> call = service.getListofFilesByPath(siteId, folderId);
		log.info("Url: {}", call.request().url().toString());
		Response<FilesAndFolderResponse> response = call.execute();
		FilesAndFolderResponse filesAndFolderResponse = null;
		if (!response.isSuccessful()) {
			log.info("Response Message: {}", response.message());
			log.info("Error: {}", response.errorBody().string());
		} else {
			filesAndFolderResponse = response.body();
			log.info("Response: {}", filesAndFolderResponse);
		}
		return filesAndFolderResponse;

	}

	public InputStream downloadFile(String driveId, String fileId) throws IOException {
		SharepointService service = ServiceFactory.createService(SharepointService.class);
		Call<ResponseBody> call = service.downloadFile(driveId, fileId);
		log.info("Url: {}", call.request().url().toString());
		Response<ResponseBody> response = call.execute();
		InputStream inputstream = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			ResponseBody body = response.body();
			log.info("Response: {}", body);
			//FileUtils.writeResponseBodyToDisk(body, fileName);
			inputstream =  FileUtils.getFileInputStream(body);
		}
		return inputstream;
	}

}
