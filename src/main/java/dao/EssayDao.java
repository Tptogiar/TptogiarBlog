package dao;

import pojo.Essay;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface EssayDao extends BaseDao<Essay> {


    /**
     * 查询文章简介，用于首页
     * @param begin
     * @param pageSize
     * @return
     */
    List<Essay> queryEssayProfilesForIndex(int begin, int pageSize);

    /**
     * 查询文章总数
     * @return
     */
    Integer queryForEssayTotalCount();

    /**
     * 查询文章简介，用于文章管理页面
     * @param begin
     * @param pageSize
     * @return
     */
    List<Essay> queryEssayProfilesForManager(Integer userId, int begin, int pageSize);

    int deleteEssay(Essay essay);

    /**
     * 更新文章
     * @param essay
     * @return
     */
    int updateEssay(Essay essay);

    /**
     * 更新浏览量
     * @param fieldValueMap
     * @return
     * @throws SQLException
     */
    int updateBrowserCount(Map<String, String> fieldValueMap) throws SQLException;
}
