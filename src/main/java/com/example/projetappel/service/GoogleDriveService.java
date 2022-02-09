package com.example.projetappel.service;

import com.example.projetappel.config.DriveQuickstart;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GoogleDriveService {

    public static final Drive DRIVE_SERVICE = DriveQuickstart.getDrive();

    public static String createDirectory(String directoryName) {
        File fileMetadata = new File();
        File file = null;
        try {
            fileMetadata.setName(directoryName);
            fileMetadata.setMimeType("application/vnd.google-apps.folder");
            file = DRIVE_SERVICE.files().create(fileMetadata)
                    .setFields("id")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert file != null;
        return file.getId();
    }

    public static String getFileIdByName(String fileName) {
        String fileId = null;
        for (File file : getFiles()) {
            if (Objects.equals(file.getName(), fileName)) {
                fileId = file.getId();
                break;
            }
        }
        return fileId;
    }

    public static File getFile(String fileId) {
        File file = null;
        try {
            file = DRIVE_SERVICE.files().get(fileId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static List<File> getFiles() {
        FileList fileList;
        List<File> files = null;
        try {
            fileList = DRIVE_SERVICE.files().list()
            .setPageSize(10)
            .setFields("nextPageToken, files(id, name)")
            .execute();
            files = fileList.getFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }



    public static void deleteFile(String fileId) {
        try {
            DRIVE_SERVICE.files().delete(fileId).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createFile(String fileName, String contentType, java.io.File filePath) {
        String folderId = "0BwwA4oUTeiV1TGRPeTVjaWRDY1E";
        File fileMetadata = new File();
        fileMetadata.setName(fileName);
//        fileMetadata.setParents(Collections.singletonList(folderId));
//        java.io.File filePath = new java.io.File("/photo.png");
        FileContent mediaContent = new FileContent(contentType, filePath);
        File file = new File();
        try {
            file = DRIVE_SERVICE.files().create(fileMetadata, mediaContent)
                    .setFields("id, parents")
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getId();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            createDirectory(String.valueOf(i));
        }

//        getFiles().forEach(file -> {
//            System.out.println(file.getId());
//        });

        //createFile();

//        java.io.File file = new java.io.File("files/index.html");
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
