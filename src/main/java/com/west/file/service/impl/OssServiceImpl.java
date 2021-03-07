package com.west.file.service.impl;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.west.file.service.OssService;
import com.west.file.util.OssUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * oss service 实现
 *
 * @author west
 * @date 2021/3/1 11:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OssServiceImpl implements OssService {

    private final OssUtils ossUtils;

    @Override
    public List<Bucket> getAllBuckets() {
        return ossUtils.getAllBuckets();
    }

    @Override
    public List<S3ObjectSummary> getObjectsByBucketName(String bucketName, String prefix, boolean recursive) {
        return ossUtils.getAllObjectsByPrefix(bucketName, prefix, recursive);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        return ossUtils.getObjectURL(bucketName, objectName, 1);
    }
}
