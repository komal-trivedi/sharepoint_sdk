package com.microsoft.client.onedrive;

import java.io.IOException;
import java.io.InputStream;

import com.microsoft.dto.onedrive.DriveFileResponse;
import com.microsoft.dto.onedrive.DriveIdResponse;
import com.microsoft.dto.onedrive.DriveRootResponse;
import com.microsoft.dto.onedrive.UserInfoResponse;
import com.microsoft.service.OneDriveService;
import com.microsoft.service.ServiceFactory;
import com.microsoft.util.FileUtils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

@Slf4j
public class OneDriveClient {

	public DriveIdResponse getDriveId() throws IOException {
		OneDriveService service = ServiceFactory.createService(OneDriveService.class);
		Call<DriveIdResponse> call = service.getDriveId();
		log.info("Url: {}", call.request().url().toString());
		Response<DriveIdResponse> response = call.execute();
		DriveIdResponse driveIdResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			driveIdResponse = response.body();
			log.info("Response: {}", driveIdResponse);
		}
		return driveIdResponse;

	}

	public UserInfoResponse getCurrentUserInfo() throws IOException {
		OneDriveService service = ServiceFactory.createService(OneDriveService.class);
		Call<UserInfoResponse> call = service.getCurrentUserInfo();
		log.info("Url: {}", call.request().url().toString());
		Response<UserInfoResponse> response = call.execute();
		UserInfoResponse userInfoResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			userInfoResponse = response.body();
			log.info("Response: {}", userInfoResponse);
		}
		return userInfoResponse;

	}

	public DriveRootResponse getDriveRoot(String driveId) throws IOException {
		OneDriveService service = ServiceFactory.createService(OneDriveService.class);
		Call<DriveRootResponse> call = service.getDriveRoot(driveId);
		log.info("Url: {}", call.request().url().toString());
		Response<DriveRootResponse> response = call.execute();
		DriveRootResponse driveRootResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			driveRootResponse = response.body();
			log.info("Response: {}", driveRootResponse);
		}
		return driveRootResponse;

	}

	public DriveFileResponse getDriveFiles(String driveId, String folderId) throws IOException {
		OneDriveService service = ServiceFactory.createService(OneDriveService.class);
		Call<DriveFileResponse> call = service.getDriveFiles(driveId, folderId);
		log.info("Url: {}", call.request().url().toString());
		Response<DriveFileResponse> response = call.execute();
		DriveFileResponse driveFileResponse = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			driveFileResponse = response.body();
			log.info("Response: {}", driveFileResponse);
		}
		return driveFileResponse;
	}

	public InputStream downloadFile(String driveId, String fileId) throws IOException {
		OneDriveService service = ServiceFactory.createService(OneDriveService.class);
		Call<ResponseBody> call = service.downloadFile(driveId, fileId);
		log.info("Url: {}", call.request().url().toString());
		Response<ResponseBody> response = call.execute();
		InputStream inputstream = null;
		if (!response.isSuccessful()) {
			log.info("Error: {}", response.errorBody().string());
		} else {
			ResponseBody body = response.body();
			log.info("Response: {}", body);
			inputstream = FileUtils.getFileInputStream(body);
		}
		return inputstream;

	}

}
