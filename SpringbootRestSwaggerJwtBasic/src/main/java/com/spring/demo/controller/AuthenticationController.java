package com.spring.demo.controller;

import com.spring.demo.dto.AuthTokenDto;
import com.spring.demo.jwt.security.AuthUserPrincipal;
import com.spring.demo.jwt.security.JwtAuthenticationRequest;
import com.spring.demo.jwt.security.TokenHelper;
import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@Api(tags = ("Authentication"))
@RestController
@RequestMapping(value = { "api/auth" }, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	private static Logger log = LogManager.getLogger(AuthenticationController.class);
	
	@Autowired
	TokenHelper tokenHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;


	@ApiOperation(value = "Login User", notes = "Login User")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws AuthenticationException, IOException {

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Inject into security context
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// token creation
		AuthUserPrincipal user = (AuthUserPrincipal) authentication.getPrincipal();
		String role = "";
		if (user != null && user.getAuthorities() != null && user.getAuthorities().iterator().hasNext())
			role = user.getAuthorities().iterator().next().getAuthority();

		String jwt = tokenHelper.generateToken(user.getUsername(), Long.toString(user.getId()), user.getFullName(), role);
		int exp = tokenHelper.getExpiration();

		// Add cookie to response
		response.addCookie(createAuthCookie(jwt, exp));
		// Return the token
		return ResponseEntity.ok(new AuthTokenDto(jwt, exp));
	}

	@ApiOperation(value = "Update Token", notes = "Update Token")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "header") })
	@RequestMapping(value = { "/refresh" }, method = RequestMethod.GET)
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		String authToken = tokenHelper.getToken(request);

		if (authToken != null && principal != null) {

			// TODO check user password last update
			String refreshedToken = tokenHelper.refreshToken(authToken);
			int expiration = tokenHelper.getExpiration();

			// Add cookie to response
			response.addCookie(createAuthCookie(refreshedToken, expiration));

			return ResponseEntity.ok(new AuthTokenDto(refreshedToken, expiration));
		} else {
			return ResponseEntity.accepted().body(new AuthTokenDto());
		}
	}

	@ApiOperation(value = "User Logout", notes = "User Logout")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "token", required = true, dataType = "String", paramType = "header") })
	@GetMapping("/logout")
	ResponseEntity<?> get(HttpServletRequest request) {
	
		String token = tokenHelper.getToken(request);
		//if (token != null)
		//	sessionTokenService.delete(token);
		return ResponseEntity.ok("success");
	}

	private Cookie createAuthCookie(String jwt, int expiration) {
		Cookie authCookie = new Cookie(tokenHelper.AUTH_TOKEN_COOKIE, (jwt));
		authCookie.setPath("/");
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(expiration);
		return authCookie;
	}

	

}
