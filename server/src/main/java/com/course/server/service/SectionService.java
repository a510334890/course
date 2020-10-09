package com.course.server.service;

import com.course.server.domain.Section;
import com.course.server.domain.SectionExample;
import com.course.server.dto.SectionDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.SectionMapper;
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

@Service
public class SectionService {
    @Resource
    private SectionMapper sectionMapper;
    public void list(PageDto pageDto){
        //第三方分页插件/只要从前端传入页数和条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("id asc");//根据id进行排序查询
        /*new TestExample().setOrderByClause("id");*/
                sectionExample.setOrderByClause("sort asc");
        List<Section> sections = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sections);
        pageDto.setTotal(pageInfo.getTotal());
        List<SectionDto> sectionDtos = new ArrayList<>();
        //chapter的数据复制到chapterDto中，因为我们始终是要对dto来操作数据，
        //chapter在业务需求中是不给动的，维护起来比较麻烦
        for (int i = 0; i < sections.size(); i++) {
            Section section = sections.get(i);
    SectionDto sectionDto = new SectionDto();
            BeanUtils.copyProperties(section,sectionDto);
    sectionDtos.add(sectionDto);
        }
        pageDto.setList(sectionDtos);
    }
    //单独抽取方法，判断传进来的数据是否有id，没有就到添加数据，有就更新
    public void save(SectionDto sectionDto){
        Section section = CopyUtil.copy(sectionDto, Section.class);
        if(StringUtils.isEmpty(sectionDto.getId())){
            this.insert(section);
        }else{
            this.update(section);
        }
    }
    private void insert(Section section){
        section.setId(UuidUtil.getShortUuid());
    sectionMapper.insert(section);
    }
    private void update(Section section){
    sectionMapper.updateByPrimaryKey(section);
    }
    public void delete(String id){
    sectionMapper.deleteByPrimaryKey(id);
    }
}
