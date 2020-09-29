package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
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
public class CourseService {
    @Resource
    private CourseMapper courseMapper;
    public void list(PageDto pageDto){
        //第三方分页插件/只要从前端传入页数和条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("id asc");//根据id进行排序查询
        /*new TestExample().setOrderByClause("id");*/
        List<Course> courses = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courses);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseDto> courseDtos = new ArrayList<>();
        //chapter的数据复制到chapterDto中，因为我们始终是要对dto来操作数据，
        //chapter在业务需求中是不给动的，维护起来比较麻烦
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
    CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course,courseDto);
    courseDtos.add(courseDto);
        }
        pageDto.setList(courseDtos);
    }
    //单独抽取方法，判断传进来的数据是否有id，没有就到添加数据，有就更新
    public void save(CourseDto courseDto){
        Course course = CopyUtil.copy(courseDto, Course.class);
        if(StringUtils.isEmpty(courseDto.getId())){
            this.insert(course);
        }else{
            this.update(course);
        }
    }
    private void insert(Course course){
        course.setId(UuidUtil.getShortUuid());
    courseMapper.insert(course);
    }
    private void update(Course course){
    courseMapper.updateByPrimaryKey(course);
    }
    public void delete(String id){
    courseMapper.deleteByPrimaryKey(id);
    }
}
