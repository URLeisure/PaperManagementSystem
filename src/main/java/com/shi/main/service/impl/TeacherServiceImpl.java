package com.shi.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import com.shi.main.mapper.TeacherMapper;
import com.shi.main.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {

    @Override
    public boolean login(Teacher teacher) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Teacher::getUsername,teacher.getUsername());
        wrapper.eq(Teacher::getPassword,teacher.getPassword());
        Teacher loginTeacher = this.getOne(wrapper);
        if(loginTeacher != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(Teacher teacher){
        return this.baseMapper.updatePasswordByUsername(teacher) > 0;
    }

    @Override
    public Teacher getByUsername(Teacher teacher) {
        return this.baseMapper.selectByUsername(teacher);
    }

    @Override
    public List<Student> selectMyStudent(Teacher teacher1) {
        return this.baseMapper.selectMyStudent(teacher1);
    }

    @Override
    public List<Paper> queryAllPapers(Teacher teacher) {
        return this.baseMapper.queryMyStudentsThesis(teacher);
    }

    @Override
    public boolean updatePaper(Paper paper) {
        return this.baseMapper.updatePaper(paper) > 0;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        return this.baseMapper.insert(teacher) > 0;
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) {
        return this.baseMapper.deleteByUsername(teacher) > 0;
    }

}
