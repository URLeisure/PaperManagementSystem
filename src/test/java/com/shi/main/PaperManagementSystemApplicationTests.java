package com.shi.main;

import com.shi.main.entity.Student;
import com.shi.main.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class PaperManagementSystemApplicationTests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    void contextLoads() {
        List<Student> list = studentMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
