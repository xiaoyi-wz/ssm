package com.xiaoyi.service;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import com.xiaoyi.entity.Survey;
import com.xiaoyi.mapper.SurveyDao;
import com.xiaoyi.utils.BeanMapUtils;
import com.xiaoyi.utils.Constant;
import com.xiaoyi.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional
@Service
public class SurveyService {
    @Autowired
    private SurveyDao surveyDao;

    public int create(Survey survey) {
       return surveyDao.create(survey);
    }
    public int deleteBatch(String ids){
        int flag = 0;
        List<String> list = Splitter.on(",").splitToList(ids);
        for (String s : list) {
            surveyDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }
    public int delete(Integer id){
        return surveyDao.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int update(Survey survey){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(survey)).addId(survey.getId()).getMap();
        return surveyDao.update(map);
    }
    public List<Survey> query(Survey survey){
        PageHelper.startPage(survey.getPage(),survey.getLimit());
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(survey)).getMap();
        return surveyDao.query(map);
    }
    public Survey detail(Integer id){
        return surveyDao.detail(MapParameter.getInstance().addId(id).getMap()) ;
    }
    public int count(Survey survey){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(survey)).getMap();
        return surveyDao.count(map);
    }
    public boolean saveFile(Survey survey,MultipartFile file) {

        if (file != null) {
            // 原始文件名

            String originalFileName = file.getOriginalFilename();
            // 获取图片后缀

            String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            // 生成图片存储的名称，UUID 避免相同图片名冲突，并加上图片后缀
            String fileName = UUID.randomUUID().toString() + suffix;
            // 图片存储路径
            String filePath = Constant.IMG_PATH + fileName;
            File saveFile = new File(filePath);

            try {
                // 将上传的文件保存到服务器文件系统
                file.transferTo(saveFile);
                // 记录服务器文件系统图片名称
                survey.setLogo(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return surveyDao.create(survey) > 0;
    }

}
