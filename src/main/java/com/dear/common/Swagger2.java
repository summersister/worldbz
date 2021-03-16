package com.dear.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.show}")
    private boolean swaggerShow;

    private Tag[] getTags() {
        Tag[] tags = {};
        return tags;
    }

    @Bean
    public Docket createRestApi() {
        ParameterBuilder par = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        par.name("Authorization").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(par.build());

        par = new ParameterBuilder();
        par.name("from").description("操作源").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(par.build());

        par = new ParameterBuilder();
        par.name("lang").description("语言类型").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(par.build());

        par = new ParameterBuilder();
        par.name("country").description("国家").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(par.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dear"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("dear.0 API 列表")
                .description("测试 API调用")
                .version("1.0")
                .build();
    }
}
