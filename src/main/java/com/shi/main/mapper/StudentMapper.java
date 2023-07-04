package com.shi.main.mapper;

import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shi.main.entity.Teacher;
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
public interface StudentMapper extends BaseMapper<Student> {
    @Select({
            "select",
            "id, username, password, tid, name",
            "from student",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="tid", property="tid", jdbcType=JdbcType.INTEGER),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    Student selectByUsername(Student student);

    @Update({
            "update student",
            "set password = #{password,jdbcType=VARCHAR}",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    int updatePasswordByUsername(Student record);

    @Select({
            "select",
            "username, name",
            "from teacher",
            "where username = #{tid,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType= JdbcType.INTEGER, id=true),
            @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<Teacher> selectTeacher(Student student1);

    @Select({
            "select",
            "sid, pname, pscore, pcomment",
            "from paper",
            "where sid = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="sid", property="sid", jdbcType=JdbcType.VARCHAR),
            @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
            @Result(column="pscore", property="pscore", jdbcType=JdbcType.INTEGER),
            @Result(column="pcomment", property="pcomment", jdbcType=JdbcType.VARCHAR)
    })
    List<Paper> queryMyStudentsPaper(Student student1);

    @Update({
            "update paper",
            "set pname = #{pname,jdbcType=VARCHAR}",
            "where sid = #{sid,jdbcType=VARCHAR}"
    })
    int updatePaper(Paper paper);

    @Delete({
            "delete from student",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    int deleteByUsername(Student student);
}
