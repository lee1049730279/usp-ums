package com.usp.ums.config;

import com.usp.framework.service.Gateway;
import com.usp.framework.web.filter.JsonToParameterFilter;
import com.usp.framework.web.filter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedFormContentFilter;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.Servlet;
import java.util.HashMap;
import java.util.Map;

/**
 * web.xml spring boot配置
 * 
 * @author gileschu
 *
 */
@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<? extends Filter> jsonToParameterFilter() {
		FilterRegistrationBean<JsonToParameterFilter> registration = new FilterRegistrationBean<JsonToParameterFilter>();
		registration.setFilter(new JsonToParameterFilter());
		// 设定匹配的路径
		registration.addUrlPatterns("/gateway.do");
		Map<String, String> initParameters = new HashMap<>();
		registration.setInitParameters(initParameters);
//		registration.setDispatcherTypes(DispatcherType.FORWARD);
		registration.setOrder(1);
		// 设定加载的顺序
		return registration;
	}


	@Bean
	public FilterRegistrationBean<? extends Filter> xssFilter() {
		FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<XssFilter>();
		registration.setFilter(new XssFilter());
		// 设定匹配的路径
		registration.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		// 是否对serviceUrl进行编码，默认true：设置false可以在302对URL跳转时取消显示;jsessionid=xxx的字符串
		// 观察CommonUtils.constructServiceUrl方法可以看到
		registration.setInitParameters(initParameters);
		// 设定加载的顺序
		registration.setOrder(3);
		return registration;
	}

	/**
	 * hiddenmethod: filter: enabled: false
	 * 
	 * @return
	 */
//	 @Bean
	public FilterRegistrationBean<? extends Filter> hiddenHttpMethodFilter() {
		FilterRegistrationBean<OrderedHiddenHttpMethodFilter> registration = new FilterRegistrationBean<OrderedHiddenHttpMethodFilter>();
		registration.setFilter(new OrderedHiddenHttpMethodFilter());
		// 设定匹配的路径
		registration.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		// 是否对serviceUrl进行编码，默认true：设置false可以在302对URL跳转时取消显示;jsessionid=xxx的字符串
		// 观察CommonUtils.constructServiceUrl方法可以看到
		registration.setInitParameters(initParameters);
		registration.setOrder(4);
		// 设定加载的顺序
		return registration;
	}

	/**
	 * formcontent: filter: enabled: false
	 * 
	 * @return
	 */
//	 @Bean
	public FilterRegistrationBean<? extends Filter> formContentFilter() {
		FilterRegistrationBean<OrderedFormContentFilter> registration = new FilterRegistrationBean<OrderedFormContentFilter>();
		registration.setFilter(new OrderedFormContentFilter());
		// 设定匹配的路径
		registration.addUrlPatterns("/*");
		Map<String, String> initParameters = new HashMap<>();
		// 是否对serviceUrl进行编码，默认true：设置false可以在302对URL跳转时取消显示;jsessionid=xxx的字符串
		// 观察CommonUtils.constructServiceUrl方法可以看到
		registration.setInitParameters(initParameters);
		registration.setOrder(5);
		// 设定加载的顺序
		return registration;
	}

	@Bean
	public ServletRegistrationBean<? extends Servlet> gateway() {
		ServletRegistrationBean<Gateway> registration = new ServletRegistrationBean<Gateway>();
		registration.setServlet(new Gateway());
		// 设定匹配的路径
		registration.addUrlMappings("/gateway.do");
		Map<String, String> initParameters = new HashMap<>();
		// 是否对serviceUrl进行编码，默认true：设置false可以在302对URL跳转时取消显示;jsessionid=xxx的字符串
		// 观察CommonUtils.constructServiceUrl方法可以看到
		registration.setInitParameters(initParameters);
		// 设定加载的顺序
		return registration;
	}

}
