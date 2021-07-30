package utils;

import com.google.gson.Gson;
import dto.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Pattern;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
import static javafx.scene.input.KeyCode.Z;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/25-00:25
 */


public class WebUtils {

    private Logger logger = LoggerFactory.getLogger(WebUtils.class);
    public static Gson gson=new Gson();
    public static HashMap<String , Object> resultMap = new HashMap<>();

    /**
     * 在用户注册时在服务端重新检查用户名，密码，邮箱是否合法
     * @param username
     * @param password
     * @param email
     * @return
     */
    public static boolean checkUserDataToRegist(String username, String password, String email) {
        if (! checkUsernameToRegist(username)){
            return false;
        }
        if (! checkEmailToRegist(email)){
            return false;
        }
        if (! checkPassword(password)){
            return false;
        }
        return true;
    }

    /**
     * 在用户注册时在服务端重新检查用户名是否合法
     * @param username
     * @return
     */
    public static boolean checkUsernameToRegist(String username){
        String usernamePatt = "^[a-zA-Z0-9_]{5,12}$";
        boolean userResult = Pattern.matches(usernamePatt, username);
        if (! userResult){
            return false;
        }
        return true;
    }

    /**
     * 在用户注册时在服务端重新检查邮箱是否合法
     * @param email
     * @return
     */
    public static boolean checkEmailToRegist(String email){
        String emailPatt = "^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$";
        boolean emailResult = Pattern.matches(emailPatt, email);
        if (! emailResult){
            return false;
        }
        return true;
    }

    /**
     * 在用户注册以及修改密码时在服务端重新检查密码是否合法
     * @return
     */
    public static boolean checkPassword(String password){
        String passwordPatt = "^[a-zA-Z]\\w{5,17}$";
        boolean passwordResult = Pattern.matches(passwordPatt, password);
        if (!passwordResult){
            return false;
        }
        return true;
    }

    /**
     * 给予客户端请求失败答复
     * @param resp
     * @param msg
     * @throws IOException
     */
    public static void replyForFail(HttpServletResponse resp, String msg) throws IOException {
        resultMap.put("resultCode", ResultCode.FAIL.code);
        resultMap.put("msg",msg);
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }


    /**
     * 给予客户端请求成功答复
     * @param resp
     * @throws IOException
     */
    public static void replyForSuccess(HttpServletResponse resp) throws IOException {
        resultMap.put("resultCode", ResultCode.SUCCEED.code);
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }


    /**
     * 给予客户端请求成功答复，并给予相关数据
     * @param resp
     * @param resultMap
     * @throws IOException
     */
    public static void replyForData(HttpServletResponse resp,HashMap<String , Object> resultMap) throws IOException {
        resultMap.put("resultCode",ResultCode.SUCCEED.code);
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }


    /**
     * 检查验证码是否正确
     * @param req
     * @param code
     * @return
     */
    public static boolean checkCode(HttpServletRequest req, String code) {
        String codeToken = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute("KAPTCHA_SESSION_KEY");
        //在session中没有检查到验证码  或  验证码不正确
        if(codeToken==null || ! codeToken.equalsIgnoreCase(code)){
            return false;
        }
        return true;
    }


    /**
     * 在用户发表文章时检查合法性
     * @param title
     * @param summary
     * @param content
     * @param userId
     * @param unkonw
     * @return
     */
    public static boolean checkEssayDataToIssue(String title, StringBuilder summary, String content, Integer userId, StringBuilder unkonw) {
        if (title.length()<2 || null==content ||"".equals(content) || title.length()>30){
            unkonw.append("内容有误");
            return false;
        }
        if ("".equals(summary.toString())){
            summary.append(content.substring(0,150));
        }
        if (content.length()>50000){
            unkonw.append("暂不支持长篇大论");
            return false;
        }
        if (userId==null){
            unkonw.append("请先登录");
            return false;
        }
        return true;
    }


    /**
     * 将数字的字符串形式转换为数值形，转换出错则按默认值传出
     * @param number
     * @param defaultVale
     * @return
     */
    public static int parseToInt(String number,Integer defaultVale){
        Integer defaultInt=null;
        try {
            defaultInt= Integer.parseInt(number);
        } catch (Exception e) {
            defaultInt=defaultVale;
            e.printStackTrace();
        }
        return defaultInt;
    }


    /**
     * 判断用户登录时是使用用户名登录还是邮箱登录
     * @param emailOrUsername
     * @param password
     * @return
     */
    public static User adjustForEmailOrUsername(String emailOrUsername, String password){
        User user = new User();
        if (emailOrUsername.contains("@")){
            user.setEmail(emailOrUsername);
        }else{
            user.setUsername(emailOrUsername);
        }
        user.setPassword(password);
        return user;
    }

}
