package com.deyaco.taixingauth2;


import com.deyaco.framework.core.api.ApiResult;
import com.deyaco.taixingauth2.external.feignclient.FeignApi;
import com.deyaco.taixingauth2.support.BindUserResDto;
import com.deyaco.taixingauth2.support.RegisteredUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class TaixingUserDetailService implements UserDetailsService {
    private List<RegisteredUser> userList;
    @Autowired
    private FeignApi feignApi;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户
        MultiValueMap<String, String> query = new LinkedMultiValueMap<>();
        query.add("q-bu.username-eq", username);
        ApiResult<BindUserResDto> res = feignApi.lookupUser(query);
        if (!res.isOk() || res.getData() == null) {
            throw new UsernameNotFoundException("用户在系统中不存在:" + username);
        }
        BindUserResDto dto = res.getData();
        RegisteredUser user = new RegisteredUser(dto.getCreateUserName(), "", dto.getEnabled(), true, true, true,
                Collections.emptySet(), dto.getId(), dto.getMail(), dto.getMobile(), dto.getDisplayName(), dto.getAccountTypeEnum(),
                dto.getAdministrator(), dto.getGender(), dto.getEmployeeNumber(), dto.getAvatarFid());

        return user;
    }
}
