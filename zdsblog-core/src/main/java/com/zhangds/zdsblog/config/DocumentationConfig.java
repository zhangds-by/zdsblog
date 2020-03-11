package com.zhangds.zdsblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zhangds
 * 2020-03-11 10:08
 **/
//@Component
//@Primary
public class DocumentationConfig  implements SwaggerResourcesProvider {

    private static final String VERSION = "3.0";

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        resources.add(swaggerResource("业务管理接口", "/*/v2/api-docs", VERSION));
        resources.add(swaggerResource("代码生成接口", "/*/v2/api-docs", VERSION));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
