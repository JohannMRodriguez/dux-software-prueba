package com.dux.equipos.services;

import com.dux.equipos.application.exceptions.UnauthorizedException;
import com.dux.equipos.dto.request.RequestAuthDto;
import com.dux.equipos.dto.response.ResponseAuthDto;
import com.dux.equipos.services.implementation.AuthService;
import com.dux.equipos.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthServiceTest {

    @InjectMocks
    private AuthService service;
    @Mock
    private JwtUtil jwtUtil;

    private RequestAuthDto request;
    private ResponseAuthDto response;

    private static final String VALID_USERNAME = "testuser";
    private static final String VALID_PASSWORD = "testpass";
    private static final String MOCK_TOKEN = "mocked-jwt-token";


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ReflectionTestUtils.setField(service, "userUsername", VALID_USERNAME);
        ReflectionTestUtils.setField(service, "userPassword", VALID_PASSWORD);

    }

    @Test
    @DisplayName("auth para usuario valido")
    void testAuthenticate_Success() {

        RequestAuthDto credentials = new RequestAuthDto();
        credentials.setUsername(VALID_USERNAME);
        credentials.setPassword(VALID_PASSWORD);

        when(jwtUtil.generateToken(VALID_USERNAME)).thenReturn(MOCK_TOKEN);

        ResponseAuthDto response = service.authentication(credentials);

        assertNotNull(response);
        assertEquals(MOCK_TOKEN, response.getToken());
    }

    @Test
    @DisplayName("auth para usuario invalido")
    void testAuthenticate_Unauthorized() {

        RequestAuthDto credentials = new RequestAuthDto();
        credentials.setUsername("invalidUsername");
        credentials.setPassword(VALID_PASSWORD);

        when(jwtUtil.generateToken(VALID_USERNAME)).thenReturn(MOCK_TOKEN);

        assertThrows(UnauthorizedException.class, () -> service.authentication(credentials));

        verify(jwtUtil, never()).generateToken(anyString());
    }
}
