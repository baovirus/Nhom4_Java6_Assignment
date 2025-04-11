package com.poly.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.poly.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final AccountService accountService;

	public SecurityConfig(AccountService accountService) {
		this.accountService = accountService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(accountService::findById);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())

				// Cho phép session login (nếu cần)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

				.authorizeHttpRequests(auth -> auth
						// Phân quyền chi tiết như thầy
						.requestMatchers("/order/**").authenticated().requestMatchers("/admin/**")
						.hasAnyAuthority("STAF", "DIRE").requestMatchers("/rest/authorities/**").hasAuthority("DIRE")

						// Cho phép tất cả với các tài nguyên công cộng
						.requestMatchers("/login/**", "/register/**", "/forgot-password/**", "/product/**", "/rest/**",
								"/js/**", "/css/**", "/image/**", "/bootstrap-5.3.3-dist/**", "/")
						.permitAll()

						.anyRequest().authenticated())

				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/", true).permitAll())

				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll());

		return http.build();
	}
}
