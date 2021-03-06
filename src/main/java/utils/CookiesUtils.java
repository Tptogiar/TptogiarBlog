package utils;

import javax.servlet.http.Cookie;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/24-16:32
 */


public class CookiesUtils {


    /**
     * 获取指定的cookie
     * @param key
     * @param cookies
     * @return
     */
    public static Cookie getCookie(String key, Cookie[] cookies) {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(key.equals(name)){
                return cookie;
            }
        }
        return null;
    }
}
