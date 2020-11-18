package com.haotian.admin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.haotian.common.knife4j") //开启扫描 (knife4j接口测试html)
public class KnifeConfig {
}