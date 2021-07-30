package controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/23-22:29
 */

@WebServlet(urlPatterns = "/user")
@MultipartConfig
public class UserController extends BaseController{

    Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService = BeanFactory.getBeanIntance(UserServiceImpl.class);
    Gson gson = new Gson();
    HashMap<String , Object> resultMap=new HashMap<String , Object>();
    /**
     * 登录
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String emailOrUsername = req.getParameter("emailOrUsername");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        logger.info("收到登录请求  用户："+emailOrUsername);
        boolean isCodePass = WebUtils.checkCode(req, code);
        if(! isCodePass){
            WebUtils.replyForFail(resp,"验证码有误");
            return;
        }
        User sendUser = WebUtils.adjustForEmailOrUsername(emailOrUsername,password);
        User resultUser = userService.login(sendUser);
        if (resultUser==null){
            String msg="用户名或密码错误";
            if (sendUser.getEmail()!=""){
                if (! isExistUsernameOrEmail(req,"email",sendUser.getEmail())){
                    msg="邮箱不存在";
                }
            }if (sendUser.getUsername()!=""){
                if (! isExistUsernameOrEmail(req,"username",sendUser.getUsername())){
                    msg="用户名不存在";
                }
            }
            WebUtils.replyForFail(resp,msg);
            return;
        }
        Cookie username = new Cookie("username", resultUser.getUsername());
        Cookie userId = new Cookie("userId", resultUser.getId().toString());
        resp.addCookie(username);
        resp.addCookie(userId);
        WebUtils.replyForSuccess(resp);
        logger.info("用户 : "+emailOrUsername+"登录成功");
    }

    /**
     * 注册
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("接收到注册请求");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String code = req.getParameter("code");
        boolean isDataPass= WebUtils.checkUserDataToRegist(username,password,email);
        if( !isDataPass){
            WebUtils.replyForFail(resp,"注册信息填写有误");
            logger.info("用户："+username+"  注册失败，注册信息填写有误");
            return;
        }
        boolean isCodePass = WebUtils.checkCode(req, code);
        if(! isCodePass){
            WebUtils.replyForFail(resp,"验证码有误");
            logger.info("用户："+username+"  注册失败，验证码有误");
            return;
        }
        User registUser = userService.regist(username, password, email,WebUtils.parseToInt(gender,0));
        if (registUser==null){
            String msg="注册失败";
            if (isExistUsernameOrEmail(req,"username",username)){
                msg+=",该用户名已经被其他用户使用";
            }else if (isExistUsernameOrEmail(req,"email",email)){
                msg+=",该邮箱已经被注册";
            }
            WebUtils.replyForFail(resp,msg);
            logger.info("用户："+username+"  "+msg);
            return;
        }
        WebUtils.replyForSuccess(resp);
        logger.info("用户："+username+"  注册成功");
    }

    /**
     * 用户注册填写表单时检查用户名是否存在（是否可用于注册）
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        boolean isUsernamePass = WebUtils.checkUsernameToRegist(username);
        if (! isUsernamePass){
            WebUtils.replyForFail(resp,"用户名格式有误");
            return;
        }
        if (isExistUsernameOrEmail(req,"username",username)){
            WebUtils.replyForFail(resp,"该用户名已被其他用户使用！");
            return;
        }
        WebUtils.replyForSuccess(resp);
    }

    /**
     * 用户注册填写表单时检查邮箱地址是否存在(是否可用于注册)
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void checkEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        boolean isEmailPass = WebUtils.checkEmailToRegist(email);
        if(! isEmailPass){
            WebUtils.replyForFail(resp,"邮箱格式有误");
            return;
        }
        if (isExistUsernameOrEmail(req,"email",email)){
            WebUtils.replyForFail(resp,"该邮箱已经被注册！");
            return;
        }
        WebUtils.replyForSuccess(resp);
    }

    /**
     * 判断用户名或邮箱地址是否已经存在
     * @param req
     * @param type
     * @param content
     * @return
     */
    private boolean isExistUsernameOrEmail(HttpServletRequest req,String type,String content){
        User user = new User();
        if ("username".equals(type)){
            user.setUsername(content);
            User queryOne = userService.queryOne(user);
            if (queryOne!=null){
                return true;
            }
            return false;
        }else if ("email".equals(type)){
            user.setEmail(content);
            User queryOne = userService.queryOne(user);
            if (queryOne!=null){
                return true;
            }
            return false;
        }
        return true;
    };

    /**
     * 给予客户端用户的相关信息
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void userInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        User user = new User();
        user.setId(WebUtils.parseToInt(userId,0));
        User queryOne = userService.queryOne(user);
        if (queryOne==null){
            WebUtils.replyForFail(resp,"获取用户信息失败");
            return;
        }
        HashMap<String , Object> resultMap=new HashMap<String , Object>();
        resultMap.put("user",queryOne);
        WebUtils.replyForData(resp,resultMap);
    }

    protected void correctInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        String description = req.getParameter("description");
        String password = req.getParameter("password");
        User user = new User();
        user.setId(WebUtils.parseToInt(userId,0));
        if (description.length()>150){
            WebUtils.replyForFail(resp,"修改用户信息失败，个人简介长度超过限制");
            return;
        }
        User queryOne = userService.queryOne(user);
        if (queryOne==null){
            WebUtils.replyForFail(resp,"修改用户信息失败");
            return;
        }
        if (!WebUtils.checkPassword(password)){
            WebUtils.replyForFail(resp,"修改用户信息失败,密码不合法");
            return;
        }
        if ("".equals(description)){
            description="这家伙很懒，什么都没留下";
        }
        user.setDescription(description);
        user.setPassword(password);
        int updateOne = userService.updateOne(user);
        if (updateOne!=1){
            WebUtils.replyForFail(resp,"修改用户信息失败");
            return;
        }
        WebUtils.replyForSuccess(resp);
    }


}