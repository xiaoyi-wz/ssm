package com.xiaoyi.service;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Splitter;
import com.xiaoyi.entity.AnswerOpt;
import com.xiaoyi.mapper.AnswerOptDao;
import com.xiaoyi.utils.BeanMapUtils;
import com.xiaoyi.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AnswerOptService {
    @Autowired
    private AnswerOptDao answerOptDao;

    public int create(AnswerOpt answerOpt) {
       return answerOptDao.create(answerOpt);
    }
    public int deleteBatch(String ids){
        int flag = 0;
        List<String> list = Splitter.on(",").splitToList(ids);
        for (String s : list) {
            answerOptDao.delete(MapParameter.getInstance().addId(Integer.parseInt(s)).getMap());
            flag++;
        }
        return flag;
    }
    public int delete(Integer id){
        return answerOptDao.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int update(AnswerOpt answerOpt){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMapForUpdate(answerOpt)).addId(answerOpt.getId()).getMap();
        return answerOptDao.update(map);
    }
    public List<AnswerOpt> query(AnswerOpt answerOpt){
        PageHelper.startPage(answerOpt.getPage(),answerOpt.getLimit());
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(answerOpt)).getMap();
        return answerOptDao.query(map);
    }
    public AnswerOpt detail(Integer id){
        return answerOptDao.detail(MapParameter.getInstance().addId(id).getMap()) ;
    }
    public int count(AnswerOpt answerOpt){
        Map<String, Object> map = MapParameter.getInstance().put(BeanMapUtils.beanToMap(answerOpt)).getMap();
        return answerOptDao.count(map);
    }


}
