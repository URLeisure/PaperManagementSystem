package com.shi.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shi.main.entity.Admin;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import com.shi.main.mapper.AdminMapper;
import com.shi.main.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private TeacherServiceImpl teacherService;

    @Override
    public boolean login(Admin admin) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Admin::getUsername,admin.getUsername());
        wrapper.eq(Admin::getPassword,admin.getPassword());
        Admin loginAdmin = this.getOne(wrapper);
        if(loginAdmin != null){
            return true;
        }
        return false;
    }

    @Override
    public Admin getByUsername(Admin admin) {
        return this.baseMapper.selectByUsername(admin);
    }

    @Override
    public List<Student> selectStudents() {
        return this.baseMapper.selectStudens();
    }

    @Override
    public List<Paper> queryAllPapers() {
        return this.baseMapper.queryMyStudentsThesis();
    }

    @Override
    public List<Teacher> selectTeachers() {
        return this.baseMapper.selectAllTeacher();
    }

}
