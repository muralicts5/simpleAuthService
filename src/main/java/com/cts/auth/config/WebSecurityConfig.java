package com.cts.auth.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	public WebSecurityConfig() {
		System.out.println("insid the const");
	}
	
	 @Autowired
	    UserDetailsService userDetailsService;

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService);
	    }

	    @Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
		    return super.authenticationManagerBean();
		}
	    
	@Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		System.out.println("inside the configur2");
		httpSecurity.csrf().disable()
					.cors().configurationSource(configurationSource())
					.and()
					.authorizeRequests().antMatchers("/authenticate").permitAll()
					.anyRequest().authenticated()
					.and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	private CorsConfigurationSource configurationSource() {
	      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      CorsConfiguration config = new CorsConfiguration();
	      config.addAllowedOrigin("http://lb-frontend-1080588305.ap-south-1.elb.amazonaws.com");
	      config.setAllowCredentials(true);
	      config.addAllowedHeader("X-Requested-With");
	      config.addAllowedHeader("Content-Type");
	      config.addAllowedMethod(HttpMethod.POST);
	      source.registerCorsConfiguration("/**", config);
	      return source;
	    }
	
}
