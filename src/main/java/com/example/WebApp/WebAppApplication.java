package com.example.WebApp;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

	// @Bean
	// public ServletWebServerFactory servletContainer() {
	// TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {

	// @Override
	// protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
	// Context context = tomcat.addContext("/sources", "/opt/sources/");
	// context.setParentClassLoader(getClass().getClassLoader());
	// context.setUseHttpOnly(true);

	// Wrapper defaultServlet = context.createWrapper();
	// defaultServlet.setName("default");
	// defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
	// defaultServlet.addInitParameter("debug","0");
	// defaultServlet.addInitParameter("listings", "false");
	// defaultServlet.setLoadOnStartup(1);
	// defaultServlet.setOverridable(true);
	// context.addChild(defaultServlet);
	// context.addServletMappingDecoded("/", "default");
	// return super.getTomcatWebServer(tomcat);
	// }
	// };
	// return factory;
	// }

	@Configuration
	@EnableWebSecurity
	static class SecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
		}
	}

}
