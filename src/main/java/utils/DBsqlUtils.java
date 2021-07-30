package utils;

import pojo.User;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/22-17:59
 */


public class DBsqlUtils {

    /**
     * 组装MySQL insert语句中后半部分
     * @param object
     * @param dbFieldMap
     * @param <E>
     * @return
     */
    public static <E> String getDbFieldSqlForInsert(E object, HashMap<String, Object> dbFieldMap) {
        StringBuilder sqlBuilder = new StringBuilder("( ");
        ReflectUtils.getBeanDbFieldMap(object,dbFieldMap,"insert");
        Iterator<Map.Entry<String, Object>> iterator = dbFieldMap.entrySet().iterator();
        if (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            sqlBuilder.append(entry.getKey());
        }
        while (iterator.hasNext()){
            sqlBuilder.append(",");
            Map.Entry<String, Object> entry = iterator.next();
            sqlBuilder.append(entry.getKey());

        }
        sqlBuilder.append(" ) values ( ");
        addPlaceholder(sqlBuilder,dbFieldMap.size());
        sqlBuilder.append(" )");
        return sqlBuilder.toString();
    }

    /**
     * 组装MySQL update语句中中间数据部分
     * @param object
     * @param dbFieldMap
     * @param <E>
     * @return
     */
    public static <E> String getDbFieldSqlForUpdate(E object, HashMap<String, Object> dbFieldMap) {
        StringBuilder sqlBuilder = new StringBuilder();
        ReflectUtils.getBeanDbFieldMap(object, dbFieldMap, "update");
        Iterator<Map.Entry<String, Object>> iterator = dbFieldMap.entrySet().iterator();
        if (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            sqlBuilder.append(entry.getKey()+" = '" + entry.getValue() +"'");
        }
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            sqlBuilder.append(","+entry.getKey()+" = '" + entry.getValue() +"'");
        }
        return sqlBuilder.toString();
    }


    /**
     * 组装MySQL query语句中所查询的列部分
     * @param object
     * @param dbFieldMap
     * @param <E>
     * @return
     */
    public static <E> String getDbFieldSqlForQuery(E object, HashMap<String, Object> dbFieldMap) {
        StringBuilder sqlBuilder = new StringBuilder();
        ReflectUtils.getBeanDbFieldMap(object,dbFieldMap,"query");
        Iterator<Map.Entry<String, Object>> iterator = dbFieldMap.entrySet().iterator();
        if (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            sqlBuilder.append(entry.getKey());
        }
        while (iterator.hasNext()){
            Map.Entry<String, Object> entry = iterator.next();
            sqlBuilder.append(","+entry.getKey());
        }
        return sqlBuilder.toString();
    }



    public static void addPlaceholder(StringBuilder sqlBuilder, int size) {
        if (size>=1){
            sqlBuilder.append("?");
        }
        for (int i = 0; i <size-1 ; i++) {
            sqlBuilder.append(",?");
        }
    }

}
