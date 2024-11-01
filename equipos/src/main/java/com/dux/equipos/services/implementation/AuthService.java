package com.dux.equipos.services.implementation;

import com.dux.equipos.application.exceptions.UnauthorizedException;
import com.dux.equipos.dto.request.RequestAuthDto;
import com.dux.equipos.dto.response.ResponseAuthDto;
import com.dux.equipos.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Value("${auth.username}")
    private String userUsername;
    @Value("${auth.password}")
    private String userPassword;

    public ResponseAuthDto authentication(RequestAuthDto credentials) {

        var username = credentials.getUsername();
        var password = credentials.getPassword();

        if (StringUtils.equals(userUsername, username) && StringUtils.equals(userPassword, password)) {
            var token = jwtUtil.generateToken(username);
            return new ResponseAuthDto(token);
        }

        throw new UnauthorizedException();
    }
}
