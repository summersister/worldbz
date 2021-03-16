package com.dear.common.wxHttp;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目中需要调用微信接口获取access_token等一系列和微信接口相关的操作，
 * 我使用了Spring自带的RestTemplate类来发送Get或Post请求，
 * 直接在Spring配置文件中依赖注入
 *
 * 结果在调用微信接口的时候出了异常
 *
 * 原因：微信接口文档虽说返回的是 Json 数据，但是同时返回的 Header 里面的 Content-Type 值却是 text/plain 。
 *
 * 最终结果就是导致 RestTemplate 把数据从 HttpResponse 转换成 Object 的时候，找不到合适的 HttpMessageConverter 来转换。
 *
 * 在报错信息中知道，不支持[text/plain;charset=UTF-8]类型，需要继承 MappingJackson2HttpMessageConverter
 * 并在构造过程中设置其支持的 MediaType 类型：
 *
 */
public class WxMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public WxMappingJackson2HttpMessageConverter(){
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_HTML);
        setSupportedMediaTypes(mediaTypes);
    }
}