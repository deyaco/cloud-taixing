package com.deyaco.taixingauth2.external.feignclient;

import com.deyaco.commons.api.ApiResult;
import com.deyaco.taixingauth2.support.BindUserReqDto;
import com.deyaco.taixingauth2.support.BindUserResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient
public interface FeignApi {


    @PostMapping("/internal/users/bind")
    ApiResult<BindUserResDto> bindUser(BindUserReqDto req);

    @GetMapping("/internal/users/lookup")
    ApiResult<BindUserResDto> lookupUser(@RequestParam MultiValueMap<String, String> query);
}
