package com.west.auth.security.controller;

import com.west.auth.security.service.OnlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在线用户 api
 *
 * @author west
 * @date 2021/3/1 17:53
 */
@RestController
@RequiredArgsConstructor
public class OnlineController {

    private final OnlineService onlineService;
}
