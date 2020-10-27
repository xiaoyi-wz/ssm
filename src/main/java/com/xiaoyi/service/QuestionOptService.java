package com.xiaoyi.service;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import com.xiaoyi.entity.QuestionOpt;
import com.xiaoyi.mapper.QuestionOptDao;
import com.xiaoyi.utils.BeanMapUtils;
import com.xiaoyi.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class QuestionOptService {
    @Autowired
    private QuestionOptDao questionOptDao;

    public int create(QuestionOpt questionOpt) {
       return questionOptDao.create(questionOpt);
    }
    public int deleteBatch(String ids){
        int flag = 0;
        List<String> list = Splitter.on(",").splitToList(ids);
        for (String s : list) {
            questionOptDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }
    public int delete(Integer id){
        return questionOptDao.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int update(QuestionOpt questionOpt){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(questionOpt)).addId(questionOpt.getId()).getMap();
        return questionOptDao.update(map);
    }
    public List<QuestionOpt> query(QuestionOpt questionOpt){
        PageHelper.startPage(questionOpt.getPage(),questionOpt.getLimit());
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(questionOpt)).getMap();
        return questionOptDao.query(map);
    }
    public QuestionOpt detail(Integer id){
        return questionOptDao.detail(MapParameter.getInstance().addId(id).getMap()) ;
    }
    public int count(QuestionOpt questionOpt){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(questionOpt)).getMap();
        return questionOptDao.count(map);
    }


}
