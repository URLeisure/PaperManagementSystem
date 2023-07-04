package com.shi.main.controller;

import com.shi.main.entity.Admin;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;
import com.shi.main.service.IAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
@RestController
@RequestMapping("/admin")

public class AdminController {

    @Resource
    private IAdminService iAdminService;

    private Admin admin1 = new Admin();

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        System.out.println(admin.getUsername()+" "+admin.getPassword());
        // 处理用户注册逻辑，如创建新用户、保存到数据库等
        if(iAdminService.login(admin)){
            admin1 = iAdminService.getByUsername(admin);
            return ResponseEntity.ok("Registration successful");
        }
        // 返回成功响应
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }

    @RequestMapping("/Students")
    public List getMyStudents(){
        List<Student> list = iAdminService.selectStudents();
        return list;
    }

    @RequestMapping("/Papers")
    public List getMyPapers(){
        List<Paper> list = iAdminService.queryAllPapers();
        return list;
    }

    @RequestMapping("/Teachers")
    public List getMyTeacher(){
        List<Teacher>list = iAdminService.selectTeachers();
        return list;
    }


}
