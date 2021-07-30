package dao.impl;

import dao.EssayDao;
import pojo.Essay;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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
        if (null!=essay.getId()){
            sqlBuilder.append(" id = ? ");
        }else if(null!=essay.getAuthorId()){
            sqlBuilder.append(" author_id = ? ");
        }else if(null!=essay.getColumnId()){
            sqlBuilder.append(" column_id = ? ");
        }
        return sqlBuilder.toString();
    }

    @Override
    public List<Essay> queryEssayProfiles(int begin, int pageSize) {
        String sql="select essay_id,title,summary,author_id,author_username,publish_time,browse_count,like_count from t_essay limit ? , ?";
        return queryForList(new Essay(),sql,begin,pageSize);
    }

    @Override
    public Integer queryForEssayTotalCount(){
        String baseSql="select count(*) from {0}";
        String sql = MessageFormat.format(baseSql, getTableName());
        return ((Number)queryForSingleValue(sql)).intValue();
    }
}
