package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String toServicePath = "server//src//main//java//com//course//server//service//";
    static String toControllerPath = "business//src//main//java//com//course//business//controller//admin//";
    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        FreemarkerUtil.intiConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
        FreemarkerUtil.intiConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
    }
}