package com.west.file.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.west.comm.domain.R;
import com.west.file.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * oss api
 *
 * @author west
 * @date 2021/3/1 11:04
 */
@RestController
@RequiredArgsConstructor
public class OssController {

    private final OssService ossService;

    @GetMapping("/oss/listAllBucket")
    public R<List<Bucket>> getAllBuckets() {
        return R.ok(ossService.getAllBuckets());
    }

    @GetMapping("/oss/listObjects")
    public R<List<S3ObjectSummary>> getObjects(@RequestParam String bucketName) {
        return R.ok(ossService.getObjectsByBucketName(bucketName, "", false));
    }

    @GetMapping("/oss/objectUrl")
    public R<String> getObjectUrl(@RequestParam String bucketName, @RequestParam String objectName) {
        return R.ok(ossService.getObjectUrl(bucketName, objectName));
    }
}
