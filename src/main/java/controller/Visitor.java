package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/28-16:11
 */

@WebServlet("/visitor")
public class Visitor extends BaseController{

    Logger logger = LoggerFactory.getLogger(Visitor.class);


    protected void queryEssayProfiles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("获取文章简介");
        req.getRequestDispatcher("/essay?action=queryEssayProfiles").forward(req,resp);
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("登录");
        req.getRequestDispatcher("/user?action=login").forward(req,resp);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("注册");
        req.getRequestDispatcher("/user?action=regist").forward(req,resp);
    }

    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("检查用户名");
        req.getRequestDispatcher("/user?action=checkUsername").forward(req,resp);
    }

    protected void checkEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("检查电子邮箱");
        req.getRequestDispatcher("/user?action=checkEmail").forward(req,resp);
    }



}
