package com.west.file.service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.util.List;

/**
 * oss service
 *
 * @author west
 * @date 2021/3/1 11:04
 */
public interface OssService {

    /**
     * 获取所有 buckets
     *
     * @return buckets
     */
    List<Bucket> getAllBuckets();

    /**
     * 获取桶对象
     *
     * @param bucketName 桶
     * @param prefix     前缀
     * @param recursive  是否遍历
     * @return 文件对象列表
     */
    List<S3ObjectSummary> getObjectsByBucketName(String bucketName, String prefix, boolean recursive);

    /**
     * 获取对象
     *
     * @param bucketName 桶
     * @param objectName 对象名
     * @return 对象
     */
    String getObjectUrl(String bucketName, String objectName);
}
