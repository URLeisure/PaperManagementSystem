package com.shi.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shi.main.entity.*;
import com.shi.main.service.IPaperService;
import com.shi.main.service.IStudentService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shianyi
 * @since 2023-06-07
 */
@RestController
@RequestMapping("/student")

public class StudentController {

    @Autowired
    private IStudentService iStudentService;

    @Autowired
    private IPaperService iPaperService;
    public static Student student1= new Student();

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        List<Student> list = iStudentService.list();
        return list;
    }

    @RequestMapping("/login")
    public ResponseEntity<String> login(@RequestBody Student student) {
        // 处理用户注册逻辑，如创建新用户、保存到数据库等
        if(iStudentService.login(student)){
            student1 = iStudentService.getByUsername(student);
            return ResponseEntity.ok("Registration successful");
        }
        // 返回成功响应
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }

    @RequestMapping("/changePassword")
    public ResponseEntity<String> updatePw(@RequestBody ChangePasswordRequest changePasswordRequest){
        System.out.println(student1);
        if (!changePasswordRequest.getCurrentPassword().equals(student1.getPassword())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失败");
        }
        student1.setPassword(changePasswordRequest.getNewPassword());
        if(iStudentService.updatePassword(student1)){
            return ResponseEntity.ok("修改成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失败");
    }

    @RequestMapping("/myTeacher")
    public List getMyTeacher(){
        List<Teacher>list = iStudentService.selectMyTeacher(student1);
        list.forEach(c->System.out.println(c.getUsername()+" "+c.getName()));
        return list;
    }

    @RequestMapping("/myPaper")
    public List getMyPapers(){
        List<Paper> list = iStudentService.queryAllPaper(student1);
        return list;
    }

    @RequestMapping("/updatePaper")
    public ResponseEntity<String> updatePaper(@RequestBody Paper paper){
        paper.setSid(student1.getUsername());
    System.out.println(paper.getPname());
        if(iStudentService.updatePaper(paper)){

            return ResponseEntity.ok("修改成功");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("修改失败");
    }

    @RequestMapping("/insert")
    public ResponseEntity<String> insertStudent(@RequestBody Student student) {
        if(iStudentService.insertStudent(student)){
            return ResponseEntity.ok("Registration successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }
    @RequestMapping("/delete")
    public ResponseEntity<String> deleteTeacher(@RequestBody Student student) {
        if(iStudentService.deleteStudent(student)){
            return ResponseEntity.ok("Registration successful");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
    }


    //@GetMapping("/download")
    public ResponseEntity<Resource> downloadFile() {

        Paper paper = iPaperService.getPaperBySid(student1.getUsername());
        // 构建文件路径
        String filePath = paper.getFilepath(); // 替换为实际的文件路径

        // 创建文件资源对象
        Resource fileResource = new FileSystemResource(filePath);

        // 检查文件是否存在
        if (fileResource.exists()) {
            // 设置响应头部信息
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileResource.getFilename());

            // 返回响应对象
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload")
    public void uploadPaper(@RequestParam("file") MultipartFile file,
                            @RequestParam("title") String title) {
        iPaperService.uploadPaper(file, title, student1.getUsername());
    }
}
