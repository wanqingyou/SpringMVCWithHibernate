package com.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Order(1)
public class SpringWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		registerContextLoaderListener(servletContext);

		// Set the Jersey used property to it won't load a ContextLoaderListener
		servletContext.setInitParameter("contextConfigLocation", "");
	}

	private void registerContextLoaderListener(ServletContext servletContext) {
		WebApplicationContext webContext;
		webContext = createWebAplicationContext(ApplicationContextConfig.class);
		servletContext.addListener(new ContextLoaderListener(webContext));
		//for spring MVC dispatch
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	@SuppressWarnings("rawtypes")
	public WebApplicationContext createWebAplicationContext(Class... configClasses) {
		AnnotationConfigWebApplicationContext context;
		context = new AnnotationConfigWebApplicationContext();
		context.register(configClasses);
		return context;
	}

}