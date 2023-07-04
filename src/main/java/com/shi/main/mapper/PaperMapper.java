package com.shi.main.mapper;

import com.shi.main.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shianyi
 * @since 2023-06-08
 */
public interface PaperMapper extends BaseMapper<Paper> {

    @Select({
            "select",
            "id, sid, pname, filepath, pcomment, pscore",
            "from paper",
            "where sid = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="sid", property="sid", jdbcType=JdbcType.VARCHAR),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="filepath", property="filepath", jdbcType=JdbcType.VARCHAR),
            @Result(column="pcomment", property="pcomment", jdbcType=JdbcType.VARCHAR),
            @Result(column="pscore", property="pscore", jdbcType=JdbcType.INTEGER)
    })
    Paper selectBySid(String username);


    @Update({
            "update paper",
            "set pname = #{pname,jdbcType=VARCHAR},",
            "filepath = #{filepath,jdbcType=VARCHAR}",
            "where sid = #{sid,jdbcType=VARCHAR}"
    })
    int updateByUsername(Paper paper1);
}
