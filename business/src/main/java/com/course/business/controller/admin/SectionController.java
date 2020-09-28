package com.course.business.controller.admin;

import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.dto.ResponseDto;
import com.course.server.service.SectionService;
import com.course.server.util.ValidatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/section")
public class SectionController {
    @Autowired
    private SectionService service;
    @RequestMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }
    @RequestMapping("/save")
    public ResponseDto save(@RequestBody SectionDto sectionDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.save(sectionDto);
        responseDto.setContent(sectionDto);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        ResponseDto responseDto = new ResponseDto();
        service.delete(id);
        return responseDto;
    }
}
