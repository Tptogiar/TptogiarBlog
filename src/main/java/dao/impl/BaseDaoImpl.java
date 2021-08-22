package dao.impl;

import dao.BaseDao;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBsqlUtils;
import utils.JdbcUtils;
import utils.ReflectUtils;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/22-14:19
 */


public abstract class BaseDaoImpl<E> implements BaseDao<E>{

    public abstract String getTableName();

    public abstract String getQueryCondition(E object, ArrayList<Object> values);

    @Override
    public int insertOne(E object) {
        String baseSql="insert into {0} {1}";
        HashMap<String,Object> dbFieldMap=new HashMap<>(8);
        String appendSql = DBsqlUtils.getDbFieldSqlForInsert(object,dbFieldMap);
        String sql = MessageFormat.format(baseSql, getTableName(), appendSql);
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        try {
            Object[] insert = queryRunner.insert(sql, new ArrayHandler(), new ArrayList<>(dbFieldMap.values()).toArray());
            return ((BigInteger) insert[0]).intValue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteById(Integer id) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        String baseSql="delete from {0} where id= ? ";
        String sql = MessageFormat.format(baseSql, getTableName());
        try {
            return queryRunner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteOne(E object) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        ArrayList<Object> values = new ArrayList<>();
        String baseSql="delete from {0} where {1}";
        String conditionSql = getQueryCondition(object, values);
        String sql = MessageFormat.format(baseSql, getTableName(),conditionSql);
        try {
            return queryRunner.update(sql,values.toArray());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return 0;
    }

    @Override
    public int updateOne(E object) {
        HashMap<String,Object> dbFieldMap=new HashMap<>(8);
        ArrayList<Object> values = new ArrayList<>();
        String baseSql="update {0} set {1} where {2}";
        String updateSql= DBsqlUtils.getDbFieldSqlForUpdate(object,dbFieldMap);
        String conditionSql = getQueryCondition(object, values);
        String sql = MessageFormat.format(baseSql, getTableName(),updateSql,conditionSql);
        return updateOne(object,sql,values.toArray());
    }

    @Override
    public int updateOne(E object, String sql, Object... objects){
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        try {
            return queryRunner.update(sql,objects);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public E queryForOne(E object) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        HashMap<String,Object> dbFieldMap=new HashMap<>(8);
        ArrayList<Object> values = new ArrayList<>();
        String baseSql="select {0} from {1} where {2}";
        String conditionSql = getQueryCondition(object, values);
        String infoSql=DBsqlUtils.getDbFieldSqlForQuery(object,dbFieldMap);
        String sql = MessageFormat.format(baseSql, infoSql,getTableName(), conditionSql);
        try {
            Map<String, Object> query = queryRunner.query(sql, new MapHandler(),values.toArray());
            if(query==null){
                return null;
            }
            object= ReflectUtils.addFieldToBean(query,object);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return object;
    }


    @Override
    public List<E> queryForList(E object) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        HashMap<String,Object> dbFieldMap=new HashMap<>(8);
        ArrayList<Object> values = new ArrayList<>();
        String baseSql="select {0} from {1} where {2}";
        String infoSql=DBsqlUtils.getDbFieldSqlForQuery(object,dbFieldMap);
        String conditionSql = getQueryCondition(object, values);
        String sql = MessageFormat.format(baseSql,infoSql,getTableName(),conditionSql);
        return queryForList(object,sql);
    }


    @Override
    public List<E> queryForList(E object,String sql,Object...objects) {
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        try {
            List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(),objects);
            if(query==null){
                return null;
            }
            return  ReflectUtils.addFieldToBeans(query, object);
        } catch (SQLException | IllegalAccessException | InstantiationException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    @Override
    public Object queryForSingleValue(String sql, Object... args){
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        try {
            return queryRunner.query(sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public int batch(String sql,Object[]...values) throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        Object[][] params = new Object[values[0].length][values.length];
        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < values.length; j++) {
                params[i][j] = values[j][i];
            }
        }
        return qr.batch(sql, params).length;
    }


}
