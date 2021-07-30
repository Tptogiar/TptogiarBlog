package service;

import pojo.Essay;
import pojo.Page;

public interface EssayService {
    int issue(String title, String content, String s, Integer userId, String username);

    Page<Essay> queryEssayProfiles(int curPage, int pageSize);
}
