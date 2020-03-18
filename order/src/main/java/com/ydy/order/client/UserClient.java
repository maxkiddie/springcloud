package com.ydy.order.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.ydy.order.client.fallback.UserFallback;
import com.ydy.user.api.UserApi;

@FeignClient(name = "user-service", fallback = UserFallback.class)
public interface UserClient extends UserApi {

}
