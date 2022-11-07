package com.microsoft.client.onedrive;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.dto.onedrive.DriveFileResponse;
import com.microsoft.dto.onedrive.DriveIdResponse;
import com.microsoft.dto.onedrive.DriveRootResponse;
import com.microsoft.dto.onedrive.UserInfoResponse;
import com.microsoft.util.FileUtils;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class OneDriveClientTests {

	OneDriveClient client;

	@BeforeEach
	public void tearUp() {
		client = new OneDriveClient();
	}

	
	@Test
	public void getDriveId() throws IOException {
		DriveIdResponse driveIdResponse = client.getDriveId();
		Assert.assertNotNull(driveIdResponse);
	}

	@Test
	public void getCurrentUserInfo() throws IOException {
		UserInfoResponse userInfoResponse = client.getCurrentUserInfo();
		Assert.assertNotNull(userInfoResponse);
	}

	@Test
	public void getDriveRoot() throws IOException {
		DriveRootResponse driveRootResponse = client
				.getDriveRoot("b!FIM3ayZpQkmZ9mzzqwZYmyQxg5Fuy_BOnBZdoi7FMNIqUmjBikguRp-Q29cX-OnV");
		Assert.assertNotNull(driveRootResponse);

	}

	@Test
	public void getDriveFiles() throws IOException {
		DriveFileResponse driveFileResponse = client.getDriveFiles(
				"b!FIM3ayZpQkmZ9mzzqwZYmyQxg5Fuy_BOnBZdoi7FMNIqUmjBikguRp-Q29cX-OnV",
				"01G76XT3VXPJJ6GZACHRBLJEKIS73Q2QB7");
		Assert.assertNotNull(driveFileResponse);
	}

	@Test
	public void downloadFile() throws IOException {

		InputStream is = client.downloadFile("b!FIM3ayZpQkmZ9mzzqwZYmyQxg5Fuy_BOnBZdoi7FMNIqUmjBikguRp-Q29cX-OnV",
				"01G76XT3R3JG4DCFJIINFZ34HS4QBZ7KLG");
		Assert.assertNotNull(is);
		FileUtils.writeResponseBodyToDisk(is, is.available(), "Download.docx");
	}

}
