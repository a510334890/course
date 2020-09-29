package com.course.generator.server;

import com.course.generator.util.FreemarkerUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ServerGenerator {
    static String MODULE="business";
    static String toServicePath = "server//src//main//java//com//course//server//service//";
    static String toControllerPath = MODULE+"business//src//main//java//com//course//"+MODULE+"//controller//admin//";

    public static void main(String[] args) throws Exception {
        String Domain = "Section";
        String domain = "section";
        String tablieNameCn="小节";
        String module = MODULE;
        Map<String,Object> map = new HashMap<>();
        map.put("Domain",Domain);
        map.put("domain",domain);
        map.put("tablieNameCn",tablieNameCn);
        map.put("module",module);
        FreemarkerUtil.intiConfig("service.ftl");
        FreemarkerUtil.generator(toServicePath+Domain+"Service.java",map);
        FreemarkerUtil.intiConfig("controller.ftl");
        FreemarkerUtil.generator(toControllerPath+Domain+"Controller.java",map);
    }
}
