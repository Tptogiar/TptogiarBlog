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


    /**
     * 获取文章简介
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryEssayProfiles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/essay?action=queryEssayProfiles").forward(req,resp);
    }

    /**
     *登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user?action=login").forward(req,resp);
    }

    /**
     *注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user?action=regist").forward(req,resp);
    }

    /**
     *检查用户名
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user?action=checkUsername").forward(req,resp);
    }

    /**
     *检查电子邮箱
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/user?action=checkEmail").forward(req,resp);
    }

    /**
     *获取文章
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void wholeEssay(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        req.getRequestDispatcher("//essay?action=wholeEssay").forward(req,resp);
    }


}
