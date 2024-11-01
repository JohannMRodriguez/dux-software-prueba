package com.dux.equipos.resources;

import com.dux.equipos.application.swagger.SwaggerApiConfig;
import com.dux.equipos.dto.request.RequestAuthDto;
import com.dux.equipos.dto.response.ResponseAuthDto;
import com.dux.equipos.services.implementation.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dux.equipos.utils.Constantes.AUTHORIZATION_TAG;
import static com.dux.equipos.utils.Constantes.BASE_PATH_AUTH;

@RestController
@RequestMapping(BASE_PATH_AUTH)
@Tag(name = SwaggerApiConfig.API_AUTHORIZATION_TAG)
public class AuthResource {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    @Operation(summary = AUTHORIZATION_TAG)
    public ResponseEntity<ResponseAuthDto> authenticate(@RequestBody RequestAuthDto request) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.authentication(request));
    }
}
