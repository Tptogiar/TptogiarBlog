package service;

import pojo.Essay;
import pojo.Page;

import java.sql.SQLException;
import java.util.Map;

public interface EssayService {
    int issue(String title, String content, String s, Integer userId, String username);

    /**
     * 获取文件简介，用于首页
     * @param curPage
     * @param pageSize
     * @return
     */
    Page<Essay> queryEssayProfilesForIndex(int curPage, int pageSize);

    /**
     * 获取整片文章
     * @param essayId
     * @return
     */
    Essay wholeEssay(int essayId);

    /**
     * 获取文章简介，用于文章管理
     * @param userId
     * @param curPage
     * @param pageSize
     * @return
     */
    Page<Essay> queryEssayProfilesForManager(Integer userId, int curPage, int pageSize);

    int deleteEssay(int essayId);


    int edit(String title, String toString, String content, Integer userId, String username, int essayId);

    /**
     * 更新文章浏览量
     * @param fieldValueMap
     * @return
     * @throws SQLException
     */
    int updateBrowserCount(Map<String, String> fieldValueMap) throws SQLException;
}
