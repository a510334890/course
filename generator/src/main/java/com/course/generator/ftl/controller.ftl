package com.course.${module}.controller.admin;

import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.${Domain}Service;
import com.course.server.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

private static final String BUSINESS_NAME="${tableNameCn}";
    @Autowired
    private ${Domain}Service service;
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}Dto ${domain}Dto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        //保存校验
       <#list fieldList as field>
           <#if field.name !="id" && field.nameHump!="createAt" && field.nameHump!="updateAt" && field.nameHump!="sort">
           <#if !field.nullAble>
               ValidatorUtil.require(${domain}Dto.get${field.nameBigHump}(),"${field.nameCn}");
           </#if>
           <#if (field.length > 0)>
               ValidatorUtil.length(${domain}Dto.get${field.nameBigHump}(),"${field.nameCn}",1,${field.length});
           </#if>
           </#if>
      </#list>
        ResponseDto responseDto = new ResponseDto();
        service.save(${domain}Dto);
        responseDto.setContent(${domain}Dto);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.delete(id);
        return responseDto;
    }
}
