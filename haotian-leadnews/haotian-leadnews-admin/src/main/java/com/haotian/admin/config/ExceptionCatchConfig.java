package com.haotian.admin.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.haotian.common.excption")//开启扫描 (异常捕获)
public class ExceptionCatchConfig {
}
