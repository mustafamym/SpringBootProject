package com.spring.demo.config;

import com.spring.demo.jwt.security.AuthUserDetailsService;
import com.spring.demo.jwt.security.CorsFilter;
import com.spring.demo.jwt.security.LogoutSuccess;
import com.spring.demo.jwt.security.RestAuthenticationEntryPoint;
import com.spring.demo.jwt.security.TokenAuthenticationFilter;
import com.spring.demo.jwt.security.TokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



/**
 * Created by Gulam Mustafa 20/10/2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${app.security.jwt.cookie}")
	private String AUTH_TOKEN_COOKIE;

	@Value(value = "${app.security.ignore:}")
	private String ignoreUris;
	
	@Value(value = "${app.security.secured:/**}")
	private String securedUris;

	@Value(value = "${app.security.csrf.ignore:}")
	private String ignoreCsrfUris;

	@Autowired
	CorsFilter corsFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private AuthUserDetailsService userDetailsService;

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private LogoutSuccess logoutSuccess;

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception {
	 * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
	 * ); }
	 */

	@Autowired
	TokenHelper tokenHelper;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http

				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint).and()
				.authorizeRequests()
				// Allow pre-flight checks
				.antMatchers(HttpMethod.OPTIONS).permitAll()
				// "/auth/login" will be remove after migration
				.antMatchers(securedUris.split(",\\s*")).authenticated().and()
				.addFilterBefore(new TokenAuthenticationFilter(tokenHelper, userDetailsService),
						BasicAuthenticationFilter.class)
				.logout()
				// "/auth/login" will be remove after migration
				.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
				.logoutRequestMatcher(new AntPathRequestMatcher("/api/users/logout", "GET"))
				.logoutSuccessHandler(logoutSuccess).deleteCookies(AUTH_TOKEN_COOKIE).and()
				.addFilterBefore(corsFilter, ChannelProcessingFilter.class);

		if (ignoreCsrfUris != null && !ignoreCsrfUris.trim().isEmpty()) {
			http.csrf().ignoringAntMatchers(ignoreCsrfUris.split(",\\s*"));
		}

		// disable csrf for the login request
		http.csrf()
				/*--------------start----------------*/
				.ignoringAntMatchers("/auth/login").ignoringAntMatchers("/api/users")
				.ignoringAntMatchers("/api/users/{email}/forgot-password")
				.ignoringAntMatchers("/api/users/reset-forgot-password").ignoringAntMatchers("/api/users/activate")
				.ignoringAntMatchers("/api/users/{email}/profile-activation-email-resend")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		http.headers().frameOptions().sameOrigin();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TokenAuthenticationFilter will ignore the below paths
		if (ignoreUris != null && !ignoreUris.trim().isEmpty()) {
			web.ignoring().antMatchers(ignoreUris.split(",\\s*"));
		}
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
