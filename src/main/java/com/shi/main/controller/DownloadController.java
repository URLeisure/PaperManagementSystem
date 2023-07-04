package com.shi.main.controller;

import com.shi.main.entity.Student;
import com.shi.main.service.IPaperService;
import com.shi.main.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author: 扑腾的江鱼
 * @description: TODO 类描述
 * @create: 2023/06/09 17:12
 **/
@Controller
public class DownloadController {

    @Autowired
    private IPaperService iPaperService;

    @Autowired
    private IStudentService iStudentService;

    @RequestMapping("/teacher/download/{username}")
    public ResponseEntity<Resource> tDownloadFile(@PathVariable String username) throws UnsupportedEncodingException {
        Student student = new Student();
        student.setUsername(username);
        Student student1 = iStudentService.getByUsername(student);
        if(student1 == null){
            return ResponseEntity.notFound().build();
        }
        // 构建文件路径
        String filePath= iPaperService.getPaperBySid(username).getFilepath();
        System.out.println(filePath);


        // 创建文件资源对象
        Resource fileResource = new FileSystemResource(filePath);

        // 检查文件是否存在
        if (fileResource.exists()) {
            System.out.println(fileResource.getFilename());

            // 设置响应头部信息
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFilename = URLEncoder.encode(fileResource.getFilename(), StandardCharsets.UTF_8.toString());
            headers.setContentDispositionFormData("attachment", encodedFilename);

            // 返回响应对象
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/student/download")
    public ResponseEntity<Resource> downloadFile() throws UnsupportedEncodingException {
        // 构建文件路径
        Student student = StudentController.student1;
        System.out.println(student.getUsername());
        String filePath;
        if(student != null){
            filePath = iPaperService.getPaperBySid(student.getUsername()).getFilepath();
            System.out.println(filePath);
        }else{
            filePath = null;
        }

        // 创建文件资源对象
        Resource fileResource = new FileSystemResource(filePath);

        // 检查文件是否存在
        if (fileResource.exists()) {
            System.out.println(fileResource.getFilename());

            // 设置响应头部信息
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            String encodedFilename = URLEncoder.encode(fileResource.getFilename(), StandardCharsets.UTF_8.toString());
            headers.setContentDispositionFormData("attachment", encodedFilename);

            // 返回响应对象
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileResource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
