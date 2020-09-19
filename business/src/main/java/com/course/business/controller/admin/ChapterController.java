package com.course.business.controller.admin;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Autowired
    private ChapterService service;
    @RequestMapping("/list")
    public PageDto list(@RequestBody PageDto pageDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        service.list(pageDto);
        return pageDto;
    }
    @RequestMapping("/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto){ //因为前端是由json流的方式传递参数的所以在接收参数前要加@RequestBody
        service.save(chapterDto);
        return chapterDto;
    }
}
