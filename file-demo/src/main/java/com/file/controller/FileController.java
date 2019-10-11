package com.file.controller;

import com.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.NameValuePair;
import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName FileController
 * @Description: TODO
 * @Author yanlin
 * @Date 2019/10/9
 * @Version V1.0
 **/
//为测试使用跨域问题
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@RestController
public class FileController {

    @PostMapping(value = "/fastDFSUpload")
    public String fastDFSUpload(MultipartFile file) {

        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        String file_Name = file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return FileService.uploadFile(bytes, ext_Name, file_Name);
    }

    /**
     * @return void
     * @Author yanlin
     * @Description FastDFS实现文件下载
     * @Date 4:57 下午 2019/10/11
     * @Param [filePath]
     **/
    @GetMapping(value = "/fastDFSDownload")
    public void fastDFSDownload(String filePath) {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");

            // 链接FastDFS服务器，创建tracker和Stroage
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = FileService.getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = FileService.getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] b = storageClient.download_file("group1", filePath);
            if (b == null) {
                throw new IOException("文件" + filePath + "不存在");
            }

            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
            FileOutputStream fileOutputStream = new FileOutputStream("${user.dir}//" + fileName);
            IOUtils.write(b, fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author yanlin
     * @Description FastDFS获取将上传文件信息
     * @Date 4:56 下午 2019/10/11
     * @Param [filePath]
     **/
    @GetMapping(value = "/fastDFSGetFileInfo")
    public void fastDFSGetFileInfo(String filePath) {
        try {
            // 链接FastDFS服务器，创建tracker和Stroage
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = FileService.getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = FileService.getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            FileInfo fi = storageClient.get_file_info("group1", filePath);
            if (fi == null) {
                throw new IOException("文件" + filePath + "不存在");
            }

            log.debug("文件信息=" + fi.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author yanlin
     * @Description FastDFS获取文件名称
     * @Date 4:56 下午 2019/10/11
     * @Param [filePath]
     **/
    @GetMapping(value = "/fastDFSGetFileName")
    public void fastDFSGetFileName(String filePath) {
        try {
            // 链接FastDFS服务器，创建tracker和Stroage
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = FileService.getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = FileService.getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            NameValuePair[] nvps = storageClient.get_metadata("group1", filePath);
            if (nvps == null) {
                throw new IOException("文件" + filePath + "不存在");
            }

            log.debug(nvps[0].getName() + "." + nvps[0].getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return void
     * @Author yanlin
     * @Description FastDFS实现删除文件
     * @Date 4:57 下午 2019/10/11
     * @Param [filePath]
     **/
    @GetMapping(value = "/fastDFSDelete")
    public void fastDFSDelete(String filePath) {
        try {
            // 链接FastDFS服务器，创建tracker和Stroage
            ClientGlobal.initByProperties("fastdfs-client.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = FileService.getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = FileService.getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            int i = storageClient.delete_file("group1", filePath);
            log.debug(i == 0 ? "删除成功" : "删除失败:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
