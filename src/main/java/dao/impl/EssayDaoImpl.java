package dao.impl;

import dao.EssayDao;
import pojo.Essay;
import utils.DBsqlUtils;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/26-15:49
 */


public class EssayDaoImpl extends BaseDaoImpl<Essay> implements EssayDao {


    @Override
    public String getTableName() {
        return "t_essay";
    }

    @Override
    public String getQueryCondition(Essay essay, ArrayList<Object> values) {
        StringBuilder sqlBuilder = new StringBuilder();
        if (null!=essay.getEssayId()){
            sqlBuilder.append(" essay_id = ? ");
            values.add(essay.getEssayId());
        }else if(null!=essay.getAuthorId()){
            sqlBuilder.append(" author_id = ? ");
            values.add(essay.getAuthorId());
        }else if(null!=essay.getColumnId()){
            sqlBuilder.append(" column_id = ? ");
            values.add(essay.getColumnId());
        }
        return sqlBuilder.toString();
    }


    @Override
    public List<Essay> queryEssayProfilesForIndex(int begin, int pageSize) {
        String sql="select essay_id,title,summary,content,author_id,publish_time,last_time,browse_count,like_count,username,avatar_path " +
                "from t_essay e " +
                "inner join t_user u " +
                "on e.author_id=u.id " +
                "limit ? , ?";
        return queryForList(new Essay(),sql,begin,pageSize);
    }


    @Override
    public Integer queryForEssayTotalCount(){
        String baseSql="select count(*) from {0}";
        String sql = MessageFormat.format(baseSql, getTableName());
        return ((Number)queryForSingleValue(sql)).intValue();
    }

    @Override
    public List<Essay> queryEssayProfilesForManager(Integer userId, int begin, int pageSize) {
        String sql="select essay_id,title,publish_time,browse_count,like_count from t_essay where author_id = ? limit ? , ?";
        return queryForList(new Essay(),sql,userId,begin,pageSize);
    }

    @Override
    public int deleteEssay(Essay essay) {
        String baseSql="delete from {0} where  essay_id = ? ";
        String sql = MessageFormat.format(baseSql, getTableName());
        return updateOne(essay,sql,essay.getEssayId());
    }


    @Override
    public int updateEssay(Essay essay) {
        HashMap<String,Object> dbFieldMap=new HashMap<>(8);
        String baseSql="update {0} set {1} where  essay_id = ? and author_id = ?";
        String updateSql= DBsqlUtils.getDbFieldSqlForUpdate(essay,dbFieldMap);
        String sql = MessageFormat.format(baseSql, getTableName(),updateSql);
        return updateOne(essay,sql,essay.getEssayId(),essay.getAuthorId());
    }


    @Override
    public int updateBrowserCount(Map<String, String> fieldValueMap) throws SQLException {
        String baseSql="update {0} set browse_count = ? where essay_id = ?";
        String sql=MessageFormat.format(baseSql,getTableName());
        Object[] essayIds=fieldValueMap.keySet().toArray();
        Object[] browserCounts=fieldValueMap.values().toArray();
        return batch(sql,browserCounts,essayIds);
    }
}
