package com.shi.main.service;

import com.shi.main.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
public interface IAdminService extends IService<Admin> {

    boolean login(Admin admin);

    Admin getByUsername(Admin admin);

    List<Student> selectStudents();

    List<Paper> queryAllPapers();

    List<Teacher> selectTeachers();

}
