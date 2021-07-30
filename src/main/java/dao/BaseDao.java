package dao;

import java.util.List;

public interface BaseDao<E> {

    int insertOne(E object);

    int deleteById(Integer id);

    int deleteOne(E object);

    int updateOne(E object);

    E queryForOne(E object);

    List<E> queryForList(E object);

    List<E> queryForList(E object, String sql, Object... objects);

    Object queryForSingleValue(String sql, Object... args);
}
