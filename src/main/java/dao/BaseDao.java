package dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<E> {

    int insertOne(E object);

    int deleteById(Integer id);

    int deleteOne(E object);

    int updateOne(E object);

    int updateOne(E object, String sql, Object... objects);

    E queryForOne(E object);

    List<E> queryForList(E object);

    /**
     * 查询列表
     * @param object
     * @param sql
     * @param objects
     * @return
     */
    List<E> queryForList(E object, String sql, Object... objects);

    /**
     * 查询单个的值
     * @param sql
     * @param args
     * @return
     */
    Object queryForSingleValue(String sql, Object... args);

    /**
     * 批处理
     * @param sql
     * @param values
     * @return
     * @throws SQLException
     */
    int batch(String sql, Object[]... values) throws SQLException;
}
