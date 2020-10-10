package com.course.business.controller.admin;

import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.CourseService;
import com.course.server.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/course")
public class CourseController {

private static final String BUSINESS_NAME="课程";
    @Autowired
    private CourseService service;
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody CourseDto courseDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        //保存校验
               ValidatorUtil.require(courseDto.getName(),"名称");
               ValidatorUtil.length(courseDto.getName(),"名称",1,50);
               ValidatorUtil.length(courseDto.getSummary(),"概述",1,2000);
               ValidatorUtil.length(courseDto.getImage(),"封面",1,100);
        ResponseDto responseDto = new ResponseDto();
        service.save(courseDto);
        responseDto.setContent(courseDto);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.delete(id);
        return responseDto;
    }
}
