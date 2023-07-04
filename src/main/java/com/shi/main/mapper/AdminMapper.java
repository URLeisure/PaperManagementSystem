package com.shi.main.mapper;

import com.shi.main.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
public interface AdminMapper extends BaseMapper<Admin> {

    @Select({
            "select",
            "id, username, password",
            "from admin",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    Admin selectByUsername(Admin admin);


    @Select({
            "select",
            "username, password, name, tid",
            "from student"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="tid", property="tid", jdbcType=JdbcType.INTEGER),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Student> selectStudens();

    @Select({
            "select",
            "sid, pname, pscore, pcomment",
            "from paper"
    })
    @Results({
            @Result(column="sid", property="sid", jdbcType=JdbcType.VARCHAR),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="pscore", property="pscore", jdbcType=JdbcType.INTEGER),
            @Result(column="pcomment", property="pcomment", jdbcType=JdbcType.VARCHAR)
    })
    List<Paper> queryMyStudentsThesis();


    @Select({
            "select",
            "username, name, password",
            "from teacher"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Teacher> selectAllTeacher();
}
