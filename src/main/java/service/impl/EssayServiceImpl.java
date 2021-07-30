package service.impl;

import dao.EssayDao;
import dao.impl.EssayDaoImpl;
import factory.BeanFactory;
import pojo.Essay;
import pojo.Page;
import service.EssayService;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/26-16:13
 */


public class EssayServiceImpl  implements EssayService {

    EssayDao essayDao = BeanFactory.getBeanIntance(EssayDaoImpl.class);


    @Override
    public int issue(String title, String summary, String content, Integer userId, String username){
        Essay essay = new Essay();
        essay.setTitle(title);
        essay.setSummary(summary);
        essay.setContent(content);
        essay.setAuthorId(userId);
        essay.setAuthorUsername(username);
        LocalDateTime date =LocalDateTime.now();
        essay.setPublishTime(date);
        essay.setLastTime(date);
        int insertId = essayDao.insertOne(essay);
        if (insertId!=0){
            essay.setId(insertId);
            return insertId;
        }
        return 0;
    }

    @Override
    public Page<Essay> queryEssayProfiles(int curPage, int pageSize){
        Page<Essay> essayPage = new Page<>();
        Integer essayTotalCount = essayDao.queryForEssayTotalCount();
        int begin=essayPage.initPage(pageSize,curPage,essayTotalCount);
        List<Essay> essays = essayDao.queryEssayProfiles(begin,pageSize);
        essayPage.setItems(essays);
        return essayPage;
    }











}
