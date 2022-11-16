package com.microsoft.service;

import com.microsoft.dto.onedrive.DriveFileResponse;
import com.microsoft.dto.onedrive.DriveIdResponse;
import com.microsoft.dto.onedrive.DriveRootResponse;
import com.microsoft.dto.onedrive.UserInfoResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OneDriveService {

	@GET("v1.0/me/drive")
	public Call<DriveIdResponse> getDriveId();
	
	@GET("v1.0/me")
	public Call<UserInfoResponse> getCurrentUserInfo();
	
	@GET("v1.0/drives/{driveId}/items/root/children")
	public Call<DriveRootResponse> getDriveRoot(@Path("driveId") String driveId);
	
	@GET("v1.0/drives/{driveId}/items/{folderId}/children")
	public Call<DriveFileResponse> getDriveFiles(@Path("driveId") String driveId, @Path("folderId") String folderId);
	
	@GET("v1.0/drives/{driveId}/items/{fileId}/content")
	public Call<ResponseBody> downloadFile(@Path("driveId") String driveId, @Path("fileId") String fileId);
	
	
	
}
