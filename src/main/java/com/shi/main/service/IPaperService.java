package com.shi.main.service;

import com.shi.main.entity.Paper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shianyi
 * @since 2023-06-08
 */
public interface IPaperService extends IService<Paper> {

    void savePaper(Paper paper);

    Paper getPaperBySid(String username);

    void uploadPaper(MultipartFile file, String title, String author);

    Resource downloadPaper(String sid);
}
