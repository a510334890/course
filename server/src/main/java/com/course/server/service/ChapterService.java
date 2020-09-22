package com.course.server.service;

import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtil;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;
    public void list(PageDto pageDto){
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());//设置分页从1开始，不是0,根据最近的查询语句
        ChapterExample chapterExample = new ChapterExample();
        chapterExample.setOrderByClause("id asc");//根据id进行排序查询
        /*new TestExample().setOrderByClause("id");*/
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapters);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtos = new ArrayList<>();
        for (int i = 0; i < chapters.size(); i++) {
            Chapter chapter = chapters.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter,chapterDto);
            chapterDtos.add(chapterDto);
        }
        pageDto.setList(chapterDtos);
    }
    public void save(ChapterDto chapterDto){
        Chapter chapter = CopyUtil.copy(chapterDto, Chapter.class);
        if(!StringUtils.isEmpty(chapterDto.getId())){
            this.insert(chapter);
        }else{
            this.update(chapter);
        }
    }
    private void insert(Chapter chapter){
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }
    private void update(Chapter chapter){
        chapterMapper.updateByPrimaryKey(chapter);
    }
    public void delete(String id){
        chapterMapper.deleteByPrimaryKey(id);
    }
}
