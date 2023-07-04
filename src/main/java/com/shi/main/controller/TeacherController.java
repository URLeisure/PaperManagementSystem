package com.shi.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.shi.main.entity.ChangePasswordRequest;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.entity.Teacher;

import com.shi.main.service.IPaperService;
import com.shi.main.service.ITeacherService;

import org.apache.ibatis.annotations.ResultType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
@RequestMapping("/teacher")
public class TeacherController {
    @Resource
    private ITeacherService iTeacherService;

    private Teacher teacher1 = new Teacher();

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody Teacher teacher){
        if(iTeacherService.login(teacher)){
            teacher1 = iTeacherService.getByUsername(teacher);
            return ResponseEntity.ok("登录成功");
        }
        // 返回成功响应
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("登录失败");
    }

    @RequestMapping("/changePassword")
    public ResponseEntity<String> updatePw(@RequestBody ChangePasswordRequest changePasswordRequest){
        System.out.println(teacher1);
        if (!changePasswordRequest.getCurrentPassword().equals(teacher1.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失败");
        }
        teacher1.setPassword(changePasswordRequest.getNewPassword());
        if(iTeacherService.updatePassword(teacher1)){
            return ResponseEntity.ok("修改成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失败");
    }

    @GetMapping("/all")
    public List getAllStudents(){
        List<Teacher> list = iTeacherService.list();
        return list;
    }

    @RequestMapping("/myStudents")
    public List getMyStudents(){
        List<Student> list = iTeacherService.selectMyStudent(teacher1);
        list.forEach(c->System.out.println(c.getUsername()+" "+c.getName()));
        return list;
    }

    @RequestMapping("/myPapers")
    public List getMyPapers(){
        List<Paper> list = iTeacherService.queryAllPapers(teacher1);
        return list;


    }

    @RequestMapping("/updatePaper")
    public ResponseEntity<String> updatePaper(@RequestBody Paper paper){
        System.out.println(paper.getPname());
        if(iTeacherService.updatePaper(paper)){

            return ResponseEntity.ok("修改成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失败");
    }

    @RequestMapping("/insert")
    public ResponseEntity<String> insertTeacher(@RequestBody Teacher teacher) {
        if(iTeacherService.insertTeacher(teacher)){
            return ResponseEntity.ok("Registration successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }

    @RequestMapping("/delete")
    public String deleteTeacher(@RequestBody Teacher teacher) {
        if(iTeacherService.deleteTeacher(teacher)){
            return "student_showPaper";
        }
        return "student_upload";
    }


}
