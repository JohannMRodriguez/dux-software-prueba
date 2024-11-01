package com.dux.equipos.resources;

import com.dux.equipos.dto.request.RequestAuthDto;
import com.dux.equipos.dto.response.ResponseAuthDto;
import com.dux.equipos.services.implementation.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthResourceTest {

    @InjectMocks
    private AuthResource resource;
    @Mock
    private AuthService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("auth para usuario valido")
    void testAuthenticate_Success() {

        RequestAuthDto request = new RequestAuthDto("testuser", "password");
        ResponseAuthDto expectedResponse = new ResponseAuthDto("12345");

        when(service.authentication(request)).thenReturn(expectedResponse);

        ResponseEntity<ResponseAuthDto> response = resource.authenticate(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
