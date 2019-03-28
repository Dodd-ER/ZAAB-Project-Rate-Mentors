package com.fedex.feedbackfrog.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private OidcUserService oidcUserService;

  public WebSecurityConfig(OidcUserService oidcUserService) {
    this.oidcUserService = oidcUserService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
    .antMatcher("/**")
    .authorizeRequests()
    .antMatchers("/login")
    .permitAll()
    .anyRequest()
    .authenticated()
    .and()
    .oauth2Login()
    .redirectionEndpoint()
    .baseUri("/oauth2/callback/**")
    .and()
    .userInfoEndpoint()
    .oidcUserService(oidcUserService);
  }
}
