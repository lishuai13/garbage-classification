package com.lishuai.service;

import java.io.InputStream;

/**
 * 上传文件
 * @author lishuai
 */
public interface OssService {


    /**
     * 上传文件
     * @param inputStream
     * @param dirName
     * @param fileName
     * @param originalFilename
     * @return
     */
    String uploadFileAvatar(InputStream inputStream,String dirName, String fileName, String originalFilename);

    /**
     * 删除文件
     * @param fileName
     */
    void deleteFile(String fileName);
}
