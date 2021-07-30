package dao;

import pojo.Essay;

import java.util.List;

public interface EssayDao extends BaseDao<Essay> {


    List<Essay> queryEssayProfiles(int begin, int pageSize);

    Integer queryForEssayTotalCount();
}
