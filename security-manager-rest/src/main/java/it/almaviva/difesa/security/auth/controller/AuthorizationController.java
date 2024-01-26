package it.almaviva.difesa.security.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import it.almaviva.difesa.security.redis.entity.PostAuthResponse;
import it.almaviva.difesa.security.redis.entity.PreAuthInfoRequest;
import it.almaviva.difesa.security.user.service.CustomUserDetailsService;
import it.almaviva.difesa.shared.common.jwt.JWTHelper;
import it.almaviva.difesa.shared.common.user.CustomUserDetail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@Slf4j
@AllArgsConstructor
public class AuthorizationController {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTHelper jwtTokenUtil;

    @PostMapping("/authorize")
    public ResponseEntity<PostAuthResponse> authorize(@RequestBody PreAuthInfoRequest preAuthInfoRequest) {
    var userDetails = (CustomUserDetail) customUserDetailsService.loadUserByUsername(preAuthInfoRequest.getFiscalCode());

    Authentication authenticate = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());

    SecurityContextHolder.getContext().setAuthentication(authenticate);

    PostAuthResponse par = new PostAuthResponse();

    var token = jwtTokenUtil.generateToken(userDetails);
    var refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
    par.setToken(token);
    par.setRefreshToken(refreshToken);


    return  ResponseEntity
            .ok(par);
    	
    }

    	
}
