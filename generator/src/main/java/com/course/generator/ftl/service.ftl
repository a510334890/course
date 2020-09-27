package com.course.server.service;

import com.course.server.domain.${Domain};
import com.course.server.domain.${Domain}Example;
import com.course.server.dto.${Domain}Dto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.${Domain}Mapper;
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
public class ${Domain}Service {
    @Resource
    private ${Domain}Mapper ${domain}Mapper;
    public void list(PageDto pageDto){
        //第三方分页插件/只要从前端传入页数和条数
        PageHelper.startPage(pageDto.getPage(),pageDto.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
        ${domain}Example.setOrderByClause("id asc");//根据id进行排序查询
        /*new TestExample().setOrderByClause("id");*/
        List<${Domain}> ${domain}s = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}s);
        pageDto.setTotal(pageInfo.getTotal());
        List<${Domain}Dto> ${domain}Dtos = new ArrayList<>();
        //chapter的数据复制到chapterDto中，因为我们始终是要对dto来操作数据，
        //chapter在业务需求中是不给动的，维护起来比较麻烦
        for (int i = 0; i < ${domain}s.size(); i++) {
            ${Domain} ${domain} = ${domain}s.get(i);
    ${Domain}Dto ${domain}Dto = new ${Domain}Dto();
            BeanUtils.copyProperties(${domain},${domain}Dto);
    ${domain}Dtos.add(${domain}Dto);
        }
        pageDto.setList(${domain}Dtos);
    }
    //单独抽取方法，判断传进来的数据是否有id，没有就到添加数据，有就更新
    public void save(${Domain}Dto ${domain}Dto){
        ${Domain} ${domain} = CopyUtil.copy(${domain}Dto, ${Domain}.class);
        if(StringUtils.isEmpty(${domain}Dto.getId())){
            this.insert(${domain});
        }else{
            this.update(${domain});
        }
    }
    private void insert(${Domain} ${domain}){
        ${domain}.setId(UuidUtil.getShortUuid());
    ${domain}Mapper.insert(${domain});
    }
    private void update(${Domain} ${domain}){
    ${domain}Mapper.updateByPrimaryKey(${domain});
    }
    public void delete(String id){
    ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
