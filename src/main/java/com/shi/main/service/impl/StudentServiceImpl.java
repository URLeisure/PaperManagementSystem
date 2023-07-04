package com.shi.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import com.shi.main.mapper.PaperMapper;
import com.shi.main.mapper.StudentMapper;
import com.shi.main.service.IPaperService;
import com.shi.main.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private IPaperService iPaperService;
    @Override
    public boolean login(Student student) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Student::getUsername,student.getUsername());
        wrapper.eq(Student::getPassword,student.getPassword());
        Student loginStudent = this.getOne(wrapper);
        if(loginStudent != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(Student student) {
        return this.baseMapper.updatePasswordByUsername(student) > 0;
    }

    @Override
    public Student getByUsername(Student student) {
        return this.baseMapper.selectByUsername(student);
    }

    @Override
    public List<Teacher> selectMyTeacher(Student student1) {
        return this.baseMapper.selectTeacher(student1);
    }

    @Override
    public List<Paper> queryAllPaper(Student student1) {
        return this.baseMapper.queryMyStudentsPaper(student1);
    }

    @Override
    public boolean updatePaper(Paper paper) {
        return this.baseMapper.updatePaper(paper) > 0;
    }

    @Override
    public boolean insertStudent(Student student) {
        return this.baseMapper.insert(student) > 0;
    }

    @Override
    public boolean deleteStudent(Student student) {
        return this.baseMapper.deleteByUsername(student) > 0;
    }

    @Override
    public void uploadPaper(MultipartFile file, String title, String author) {
        try {
            // 保存文件到指定路径
            String filePath = "E:\\Code\\PaperManagementSystem\\src\\main\\resources\\directory\\" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            // 将论文信息保存到数据库
            Paper paper = new Paper();
            paper.setPname(title);
            paper.setSid(author);
            paper.setFilepath(filePath);

            Paper paper1 = iPaperService.getPaperBySid(author);
            if(paper1 != null){
                paperMapper.updateByUsername(paper1);
            }else{
                paperMapper.insert(paper);
            }

        } catch (IOException e) {
            // 处理文件上传失败的异常
            e.printStackTrace();
        }
    }
}
