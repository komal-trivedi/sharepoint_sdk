package com.microsoft.service;

import com.microsoft.dto.sharepoint.FilesAndFolderResponse;
import com.microsoft.dto.sharepoint.SitesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SharepointService {
	
	@GET("v1.0/sites/")
	public Call<SitesResponse> getAllSites(@Query("search") String search);
	
	@GET("/v1.0/sites/{path}/drive/root/children")
	public Call<FilesAndFolderResponse> getFilesAndFolders(@Path("path") String path);
	
	@GET("/v1.0/sites/{siteId}/drive/items/{folderId}/children")
	public Call<FilesAndFolderResponse> getListofFilesByPath(@Path("siteId") String siteId, @Path("folderId") String folderId);
	
	@GET("v1.0/sites/{siteId}/drive/items/{fileId}/content")
	public Call<ResponseBody> downloadFile(@Path("siteId") String siteId, @Path("fileId") String fileId);

}
