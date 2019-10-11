package com.file.service;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileService
 * @Description: TODO
 * @Author yanlin
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Slf4j
public class FileService {
    public static final char separatorChar = 0;

    public static String uploadFile(byte[] byteFile, String ext_file, String file_Name) {

        // 拼接服务区的文件路径
        StringBuffer sbPath = new StringBuffer();
        sbPath.append("http://112.126.99.144/");
        try {
            // 初始化文件资源
            ClientGlobal.initByProperties("fastdfs-client.properties");
            // 链接FastDFS服务器，创建tracker和Stroage
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            //利用字节流上传文件
//            NameValuePair[] nvps = new NameValuePair[1];
//            nvps[0] = new NameValuePair(file_Name, ext_file);
            String[] strings = storageClient.upload_file(byteFile, ext_file, null);

            sbPath.append(StrUtil.join(File.separator, strings));
            log.info("上传路径=" + sbPath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return sbPath.toString();
    }

    /**
     * 得到Storage服务
     *
     * @param storageIp
     * @return 返回Storage服务
     */
    public static StorageServer getStorageServer(String storageIp) {
        StorageServer storageServer = null;
        if (storageIp != null && !("").equals(storageIp)) {
            try {
                // ip port store_path下标
                storageServer = new StorageServer(storageIp, 23000, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("——storage server生成");
        return storageServer;
    }

    /**
     * 获得可用的storage IP
     *
     * @param trackerClient
     * @param trackerServer
     * @return 返回storage IP
     */
    public static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {
        String storageIp = null;
        if (trackerClient != null && trackerServer != null) {
            try {
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "group1");
                storageIp = storageServer.getSocket().getInetAddress().getHostAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("——获取组中可用的storage IP——" + storageIp);
        return storageIp;
    }
}
