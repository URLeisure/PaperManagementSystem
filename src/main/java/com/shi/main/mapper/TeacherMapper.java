package com.shi.main.mapper;

import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
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
public interface TeacherMapper extends BaseMapper<Teacher> {

    @Select({
            "select",
            "id, username, password, name",
            "from teacher",
            "where username = #{username,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Teacher selectByUsername(Teacher teacher);

    @Update({
            "update teacher",
            "set password = #{password,jdbcType=VARCHAR}",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    int updatePasswordByUsername(Teacher record);

    @Select({
            "select",
            "username, name",
            "from student",
            "where tid = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="tid", property="tid", jdbcType=JdbcType.INTEGER),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Student> selectMyStudent(Teacher teacher1);

    @Select({
            "select",
            "sid, pname, pscore, pcomment",
            "from paper",
            "where sid in (select sid from student where tid = #{username,jdbcType=VARCHAR})"
    })
    @Results({
            @Result(column="sid", property="sid", jdbcType=JdbcType.VARCHAR),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="pscore", property="pscore", jdbcType=JdbcType.INTEGER),
            @Result(column="pcomment", property="pcomment", jdbcType=JdbcType.VARCHAR)
    })
    List<Paper> queryMyStudentsThesis(Teacher teacher);

    @Update({
            "update paper",
            "set pcomment = #{pcomment,jdbcType=VARCHAR},",
            "pscore = #{pscore,jdbcType=INTEGER}",
            "where sid = #{sid,jdbcType=VARCHAR}"
    })
    int updatePaper(Paper record);

    @Delete({
            "delete from teacher",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByUsername(Teacher teacher);
}
