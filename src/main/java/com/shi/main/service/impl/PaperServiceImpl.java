package com.shi.main.service.impl;

import com.shi.main.controller.StudentController;
import com.shi.main.entity.Paper;
import com.shi.main.entity.Student;
import com.shi.main.mapper.PaperMapper;
import com.shi.main.service.IPaperService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shianyi
 * @since 2023-06-08
 */
@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements IPaperService {

    @Override
    public void savePaper(Paper paper) {
        this.baseMapper.insert(paper);
    }

    @Override
    public Paper getPaperBySid(String username) {
        return this.baseMapper.selectBySid(username);
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

            Paper paper1 = getPaperBySid(author);
            if(paper1 != null){
                this.baseMapper.updateByUsername(paper);
            }else{
                this.baseMapper.insert(paper);
            }

        } catch (IOException e) {
            // 处理文件上传失败的异常
            e.printStackTrace();
        }
    }

    @Override
    public Resource downloadPaper(String sid) {
        Optional<Paper> optionalPaper = Optional.ofNullable(this.baseMapper.selectBySid(sid));

        if (optionalPaper.isPresent()) {
            Paper paper = optionalPaper.get();
            File file = new File(paper.getFilepath());
            if (file.exists()) {
                System.out.println("11111111");
                return new FileSystemResource(file);
            }
        }
        return null;
    }
}

