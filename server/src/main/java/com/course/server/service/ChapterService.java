package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.mapper.ChapterMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    public List<ChapterDto> list(){
        PageHelper.startPage(1,10);//设置分页从1开始，不是0,根据最近的查询语句
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.createCriteria().andCourseIdEqualTo("00000000");
        chapterExample.setOrderByClause("id asc");//根据id进行排序查询
        /*new TestExample().setOrderByClause("id");*/
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtos = new ArrayList<>();
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtos.add(chapterDto);
        }
        return chapterDtos;
    }
}
