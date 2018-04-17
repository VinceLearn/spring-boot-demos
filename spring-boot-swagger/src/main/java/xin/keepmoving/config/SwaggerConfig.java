package xin.keepmoving.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zhourl
 * @description: spring-boot-demos
 * @version: 1.0
 * @date: 2018/4/8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)// 通过配置文件的方式，控制生成和测试环境是否要启用swagger
                .useDefaultResponseMessages(false) //关闭默认的HTTP方法的响应消息
                .globalResponseMessage(RequestMethod.GET, getDefaultResponseMessages())
                .select()
                .apis(RequestHandlerSelectors.basePackage("xin.keepmoving.api"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.ant("/api/user/*")) // 限制访问
                .build();
    }

    private List<ResponseMessage> getDefaultResponseMessages() {
        List<ResponseMessage> responseMessages = Lists.newArrayList();
        responseMessages.add(new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
//                .responseModel(new ModelRef("Error"))
                .build());
        responseMessages.add(new ResponseMessageBuilder()
                .code(403)
                .message("请求被禁止")
                .build());
        return responseMessages;
    }

    /**
     * Api文档描述信息
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot构建Swagger文档")
                .description("简单文档")
                .termsOfServiceUrl("http://localhost:8080")
                .version("1.0")
                .build();
    }
}
