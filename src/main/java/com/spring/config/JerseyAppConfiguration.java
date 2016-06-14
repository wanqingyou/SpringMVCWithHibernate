package com.spring.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/rest")
public class JerseyAppConfiguration extends ResourceConfig {
	public JerseyAppConfiguration() {
		packages("com.spring.rest");
	}
}
