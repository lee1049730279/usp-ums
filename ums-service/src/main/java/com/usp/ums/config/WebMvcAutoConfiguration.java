/**
 * 
 */
package com.usp.ums.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties.Undertow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.usp.framework.log.LogRecordAspect;
import com.usp.framework.service.UndertowServerFactoryCustomizer;
import com.usp.framework.web.interceptor.GlobalInterceptor;
import com.usp.framework.web.mvc.GlobalHandlerExceptionResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 * @author gileschu
 *
 */
@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {

	@Bean
	public LogRecordAspect logRecordAspect() {
		return new LogRecordAspect();
	}

//	@Override
//	public Validator getValidator() {
//		return new SpringValidatorAdapter(new ValidatorCollectionImpl());
//	}

	@Bean
	@ConditionalOnClass(Undertow.class)
	public UndertowServerFactoryCustomizer undertowServerFactoryCustomizer() {
		return new UndertowServerFactoryCustomizer();
	}

	@Bean
	@ConditionalOnMissingBean(RequestContextListener.class)
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.forEach(e -> {
			if (e instanceof MappingJackson2HttpMessageConverter) {
				MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) e;
				SimpleModule simpleModule = new SimpleModule();
				simpleModule.addSerializer(LocalDateTime.class,
						new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				simpleModule.addSerializer(LocalDate.class,
						new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				simpleModule.addSerializer(LocalTime.class,
						new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
				simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
				converter.getObjectMapper().registerModule(simpleModule);
			}
		});
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new GlobalHandlerExceptionResolver());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/**
		 * 放行Swagger
		 * 
		 * @see springfox.documentation.swagger.web.ApiResourceController
		 * @see springfox.documentation.swagger2.web.Swagger2Controller
		 */
		registry.addInterceptor(new GlobalInterceptor()).addPathPatterns("/**").excludePathPatterns("/error",
				"/swagger-resources", "/swagger-resources/configuration/security",
				"/swagger-resources/configuration/ui", "/v2/api-docs");
	}

}
