package com.pingpong.quoteBakery.sys.service.implementation;

import com.pingpong.quoteBakery.sys.dto.TokenDto;
import com.pingpong.quoteBakery.sys.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    @Override
    public TokenDto getCurrentTokenInfo() {
        TokenDto result = new TokenDto();

        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        result.setUid((String) requestAttributes.getAttribute("uid", 0));

        return result;
    }
}
