package com.miniblogserver.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniblogserver.dto.UserDTO;
import com.miniblogserver.model.User;
import com.miniblogserver.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private final UserService userService;

	private final ObjectMapper mapper = new ObjectMapper();

	public WebSecurityConfig(UserService userService) {
		this.userService = userService;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	WebMvcConfigurer corsConfigure() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST","PUT", "DELETE");
			}
		};
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(Customizer.withDefaults())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/users/register").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN").anyRequest().permitAll())
				.formLogin(form -> form.successHandler(new AuthenticationSuccessHandler() {
					@Override
					public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, Authentication authentication)
							throws IOException, ServletException {
						httpServletResponse.setContentType("application/json;charset=utf-8");
						User user = (User) authentication.getPrincipal();
						UserDTO userDTO = userService.convertToDTO(user);
						httpServletResponse.getWriter().write(mapper.writeValueAsString(userDTO));
					}
				}).failureHandler(new AuthenticationFailureHandler() {
					@Override
					public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
							HttpServletResponse httpServletResponse, AuthenticationException e)
							throws IOException, ServletException {
						httpServletResponse.setContentType("application/json;charset=utf-8");
					}
				}).loginProcessingUrl("/api/v1/login").usernameParameter("username").passwordParameter("password").permitAll())
				.logout(l -> l.permitAll())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/blogimg/**", "/index.html");
	}
}