package com.dear.common.wxHttp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * rest请求模板
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        return restTemplate;
    }

}
