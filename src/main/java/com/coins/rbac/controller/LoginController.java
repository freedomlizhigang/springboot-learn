package com.coins.rbac.controller;

import com.coins.rbac.request.AdminRequest;
import com.coins.rbac.service.lmpl.AdminsServiceImpl;
import com.coins.security.console.JwtRequest;
import com.coins.security.console.JwtResponse;
import com.coins.security.console.JwtTokenUtil;
import com.coins.utils.RedisUtils;
import com.coins.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService jwtInMemoryUserDetailsService;

    public LoginController(AuthenticationManager authenticationManager,
                                       JwtTokenUtil jwtTokenUtil,
                                       UserDetailsService jwtInMemoryUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtInMemoryUserDetailsService = jwtInMemoryUserDetailsService;
    }
    @Autowired
    private AdminsServiceImpl adminImpl;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody JwtRequest authenticationRequest) throws Exception{
        System.out.println("生成的token====================" + authenticationRequest.getUsername());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        System.out.println("生成的token====================" + authenticationRequest.getUsername());
        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("生成的token====================" + token);
//        eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsemciLCJleHAiOjE2NTY4NzQyNzYsImlhdCI6MTY1Njg1NjI3Nn0.5Xq70h9oVIWef5w2bXjciyHNtcFjJAlEmiWxIRdtn7w-ouDRgW2oPDGPkh0iXYxqO0Jf-GtJ_xnMJUHFDPCvOg
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
