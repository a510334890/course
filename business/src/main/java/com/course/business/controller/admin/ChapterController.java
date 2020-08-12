package com.course.business.controller.admin;

import com.course.server.domain.Chapter;
import com.course.server.domain.dto.ChapterDto;
import com.course.server.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ChapterController {
    @Autowired
    private ChapterService service;
    @RequestMapping("/chapter")
    public List<ChapterDto> test2(){
        return service.list();
    }
}
