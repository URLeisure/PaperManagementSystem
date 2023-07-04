package com.shi.main.service;

import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shi.main.entity.Teacher;
import org.springframework.web.multipart.MultipartFile;

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
public interface IStudentService extends IService<Student> {

    boolean login(Student student);

    boolean updatePassword(Student student);

    Student getByUsername(Student student);

    List<Teacher> selectMyTeacher(Student student1);

    List<Paper> queryAllPaper(Student student1);

    boolean updatePaper(Paper paper);

    boolean insertStudent(Student student);

    boolean deleteStudent(Student student);

    void uploadPaper(MultipartFile file, String title, String author);
}
