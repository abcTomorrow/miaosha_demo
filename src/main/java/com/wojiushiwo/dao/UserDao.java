package com.wojiushiwo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created by 我就是我
 * 2019/4/21 下午1:34
 */
@Mapper
@Repository
public interface UserDao {
    /**
     * @param id 主键Id
     * @return 名称
     */
    @Select("select uname from users where id=#{id}")
    String getNameById(@Param("id") Integer id);

}
