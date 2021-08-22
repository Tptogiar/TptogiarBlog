package service.impl;

import dao.EssayDao;
import dao.impl.EssayDaoImpl;
import factory.BeanFactory;
import pojo.Essay;
import pojo.Page;
import service.EssayService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/26-16:13
 */


public class EssayServiceImpl  implements EssayService {

    EssayDao essayDao = BeanFactory.getBeanIntance(EssayDaoImpl.class);
    private static JedisServiceImpl jedisService = BeanFactory.getBeanIntance(JedisServiceImpl.class);
    public static String BROWSER_KEY="browser";


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
            essay.setEssayId(insertId);
            return insertId;
        }
        return 0;
    }

    @Override
    public Page<Essay> queryEssayProfilesForIndex(int curPage, int pageSize){
        Page<Essay> essayPage = new Page<>();
        Integer essayTotalCount = essayDao.queryForEssayTotalCount();
        int begin=essayPage.initPage(pageSize,curPage,essayTotalCount);
        List<Essay> essays = essayDao.queryEssayProfilesForIndex(begin,pageSize);
        essayPage.setItems(essays);
        return essayPage;
    }

    @Override
    public Essay wholeEssay(int essayId) {
        String essayIdStr = String.valueOf(essayId);
        Essay essay = new Essay();
        essay.setEssayId(essayId);
        Essay query = essayDao.queryForOne(essay);
        if (jedisService.hget(BROWSER_KEY,essayIdStr)==null){
            String browsedCount = query.getBrowsedCount().toString();
            jedisService.hset(BROWSER_KEY,essayIdStr,browsedCount);
        }else{
            long incr = jedisService.hincrBy(BROWSER_KEY,essayIdStr,1);
            query.setBrowsedCount(Math.toIntExact(incr));
        }
        return query;
    }

    @Override
    public Page<Essay> queryEssayProfilesForManager(Integer userId, int curPage, int pageSize) {
        Page<Essay> essayPage = new Page<>();
        Integer essayTotalCount = essayDao.queryForEssayTotalCount();
        int begin=essayPage.initPage(pageSize,curPage,essayTotalCount);
        List<Essay> essays = essayDao.queryEssayProfilesForManager(userId,begin,pageSize);
        essayPage.setItems(essays);
        return essayPage;
    }

    @Override
    public int deleteEssay(int essayId) {
        Essay essay = new Essay();
        essay.setEssayId(essayId);
        return essayDao.deleteEssay(essay);
    }

    @Override
    public int edit(String title, String summary, String content, Integer userId, String username, int essayId) {
        Essay essay = new Essay();
        essay.setTitle(title);
        essay.setSummary(summary);
        essay.setContent(content);
        essay.setAuthorId(userId);
        essay.setAuthorUsername(username);
        LocalDateTime date =LocalDateTime.now();
        essay.setLastTime(date);
        essay.setEssayId(essayId);
        int insertId = essayDao.updateEssay(essay);
        if (insertId!=0){
            essay.setEssayId(insertId);
            return insertId;
        }
        return 0;
    }

    @Override
    public int updateBrowserCount(Map<String, String> fieldValueMap) throws SQLException {
       return essayDao.updateBrowserCount(fieldValueMap);
    }


}
