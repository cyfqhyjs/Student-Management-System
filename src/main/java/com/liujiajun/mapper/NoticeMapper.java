package com.liujiajun.mapper;

import com.liujiajun.po.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 插入通知
    @Insert("INSERT INTO notice(notice) VALUES (#{notice})")
    int adminNotice(String notice);

    // 查询所有通知
    @Select("SELECT * FROM notice")
    List<Notice> showNotice();




}