package com.wojiushiwo.dao;

import com.wojiushiwo.domain.model.MiaoShaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by 我就是我
 * 2019/4/21 下午8:30
 * @author wojiushiwo
 */
@Mapper
public interface MiaoShaUserDao {

    @Select("select * from miaosha_user where id=#{id}")
    MiaoShaUser getById(@Param("id") Long id);

}
