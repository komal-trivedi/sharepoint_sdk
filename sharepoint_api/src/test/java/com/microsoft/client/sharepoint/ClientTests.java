package com.microsoft.client.sharepoint;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.dto.sharepoint.FilesAndFolderResponse;
import com.microsoft.dto.sharepoint.SitesResponse;
import com.microsoft.util.FileUtils;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class ClientTests {

	SharepointClient client;

	@BeforeEach
	public void tearUp() {
		client = new SharepointClient();
	}

	@Test
	public void getAllSites() throws IOException {
		SitesResponse sitesResponse = client.getAllSites("*");
		Assert.assertNotNull(sitesResponse);
		Assert.assertTrue(sitesResponse.getValue().size() > 0);
	}

	@Test
	public void getFilesAndFolders() throws IOException {
		FilesAndFolderResponse filesAndFolderResponse = client.getFilesAndFolder(
				"contentmap.sharepoint.com,e3b23a1b-d45d-43ef-aa2b-b5072b3d55c7,033bce93-5d13-42b0-abf9-de606f247efd");
		Assert.assertNotNull(filesAndFolderResponse);
		Assert.assertTrue(filesAndFolderResponse.getValue().size() > 0);
	}

	@Test
	public void getListOfFilesByFolder() throws IOException {
		FilesAndFolderResponse filesAndFolderResponse = client
				.getListofFilesByFolder("e3b23a1b-d45d-43ef-aa2b-b5072b3d55c7", "01DZOMACH4RNDTQDQJLZCIIJ3N5L7FBORP");
		Assert.assertNotNull(filesAndFolderResponse);
		Assert.assertTrue(filesAndFolderResponse.getValue().size() > 0);
	}

	@Test
	public void getListOfFoldersOnSharedDocuments() throws IOException {
		FilesAndFolderResponse filesAndFolderResponse = client
				.getListofFilesByFolder("e3b23a1b-d45d-43ef-aa2b-b5072b3d55c7", "01DZOMACBSK3DOWYJLJBGZXFTHB3FC7OFW");
		Assert.assertNotNull(filesAndFolderResponse);
		Assert.assertTrue(filesAndFolderResponse.getValue().size() > 0);
	}

	@Test
	public void downloadFile() throws IOException {

		InputStream is = client.downloadFile("e3b23a1b-d45d-43ef-aa2b-b5072b3d55c7",
				"01DZOMACDMPFNZJGWZGJDYDZH2E3Y3VZZ2");
		Assert.assertNotNull(is);
		FileUtils.writeResponseBodyToDisk(is, is.available(), "SharepointDemo.txt");
	}

}
