package com.xiaoyi.service;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import com.xiaoyi.entity.Question;
import com.xiaoyi.mapper.QuestionDao;
import com.xiaoyi.utils.BeanMapUtils;
import com.xiaoyi.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    public int create(Question question) {
       return questionDao.create(question);
    }
    public int deleteBatch(String ids){
        int flag = 0;
        List<String> list = Splitter.on(",").splitToList(ids);
        for (String s : list) {
            questionDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }
    public int delete(Integer id){
        return questionDao.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int update(Question question){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(question)).addId(question.getId()).getMap();
        return questionDao.update(map);
    }
    public List<Question> query(Question question){
        PageHelper.startPage(question.getPage(),question.getLimit());
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(question)).getMap();
        return questionDao.query(map);
    }
    public Question detail(Integer id){
        return questionDao.detail(MapParameter.getInstance().addId(id).getMap()) ;
    }
    public int count(Question question){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(question)).getMap();
        return questionDao.count(map);
    }


}
