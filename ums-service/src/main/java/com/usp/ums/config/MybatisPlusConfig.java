/**
 * 
 */
package com.usp.ums.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

//import com.usp.framework.mybatis.query.LogicSqlInjectorConfig;

/**
 * @author gileschu
 *
 */
@Configuration
@MapperScan("com.usp.ums.mapper")
public class MybatisPlusConfig {

//	@Bean
//	public DateMetaObjectHandler metaObjectHandler() {
//		return new DateMetaObjectHandler();
//	}


	/**
	 * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
		return interceptor;
	}

//	@Bean
//	public PerformanceInterceptor performanceInterceptor() {
//		PerformanceInterceptor interceptor = new PerformanceInterceptor();
//		// 格式化sql语句
//		Properties properties = new Properties();
//		properties.setProperty("format", "true");
//		interceptor.setProperties(properties);
//		return interceptor;
//	}

}
