package com.shi.main.service;

import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
public interface ITeacherService extends IService<Teacher> {

    boolean login(Teacher teacher);

    boolean updatePassword(Teacher teacher);


    Teacher getByUsername(Teacher teacher);

    List<Student> selectMyStudent(Teacher teacher1);

    List<Paper> queryAllPapers(Teacher teacher);

    boolean updatePaper(Paper paper);

    boolean insertTeacher(Teacher teacher);

    boolean deleteTeacher(Teacher teacher);
}
