package controller;

import com.google.gson.Gson;
import factory.BeanFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.Essay;
import pojo.Page;
import pojo.User;
import service.EssayService;
import service.UserService;
import service.impl.EssayServiceImpl;
import service.impl.UserServiceImpl;
import utils.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/26-14:57
 */

@WebServlet(urlPatterns = "/essay")
public class EssayController extends BaseController{

    Logger logger = LoggerFactory.getLogger(UserController.class);
    EssayService essayService = BeanFactory.getBeanIntance(EssayServiceImpl.class);
    UserService userService = BeanFactory.getBeanIntance(UserServiceImpl.class);
    Gson gson = new Gson();


    /**
     * 发布文章
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void issue(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        StringBuilder summary = new StringBuilder(req.getParameter("summary"));
        String content = req.getParameter("content");
        Integer userId = (Integer) req.getAttribute("userId");
        StringBuilder checkReuslt=new StringBuilder();
        boolean isPass = WebUtils.checkEssayDataToIssue(title, summary,content,userId,checkReuslt);
        if(!isPass){
            WebUtils.replyForFail(resp,checkReuslt.toString());
            return;
        }
        User user = new User();
        user.setId(userId);
        User queryOne = userService.queryOne(user);
        int issue = essayService.issue(title,summary.toString(), content,userId,queryOne.getUsername());
        if (issue==0){
            WebUtils.replyForFail(resp,checkReuslt.toString());
            return;
        }
        WebUtils.replyForSuccess(resp);
    }


    /**
     * 分页获取文章简介
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void queryEssayProfiles(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int curPage = WebUtils.parseToInt(req.getParameter("curPage"), 1);
        int pageSize = WebUtils.parseToInt(req.getParameter("pageSize"), 10);
        Page<Essay> essayPage = essayService.queryEssayProfilesForIndex(curPage, pageSize);
        HashMap<String , Object> resultMap=new HashMap<String , Object>();
        resultMap.put("essays",essayPage.getItems());
        resultMap.put("totalPage",essayPage.getTotalPage());
        WebUtils.replyForData(resp,resultMap);
    }

    /**
     * 获取完整文章
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void wholeEssay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int essayId = WebUtils.parseToInt(req.getParameter("essayId"), null);
        Essay essay = essayService.wholeEssay(essayId);
        HashMap<String , Object> resultMap=new HashMap<String , Object>();
        resultMap.put("essay",essay);
        WebUtils.replyForData(resp,resultMap);
    }

    /**
     * 文章管理
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void essayManager(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int curPage = WebUtils.parseToInt(req.getParameter("curPage"), 1);
        int pageSize = WebUtils.parseToInt(req.getParameter("pageSize"), 10);
        Integer userId = (Integer) req.getAttribute("userId");
        Page<Essay> essayPage = essayService.queryEssayProfilesForManager(userId,curPage,pageSize);
        HashMap<String , Object> resultMap=new HashMap<String , Object>();
        resultMap.put("essays",essayPage.getItems());
        resultMap.put("totalPage",essayPage.getTotalPage());
        WebUtils.replyForData(resp,resultMap);
    }


    /**
     * 删除文章
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void removeEssay(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        int essayId = WebUtils.parseToInt(req.getParameter("essayId"), null);
        int deleteEssay = essayService.deleteEssay(essayId);
        if (deleteEssay!=1){
            WebUtils.replyForFail(resp,"删除失败");
            return;
        }
        WebUtils.replyForSuccess(resp);
    }


    /**
     * 编辑文章
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String title = req.getParameter("title");
        StringBuilder summary = new StringBuilder(req.getParameter("summary"));
        String content = req.getParameter("content");
        int essayId = WebUtils.parseToInt(req.getParameter("essayId"), null);
        Integer userId = (Integer) req.getAttribute("userId");
        StringBuilder checkReuslt=new StringBuilder();
        boolean isPass = WebUtils.checkEssayDataToIssue(title, summary,content,userId,checkReuslt);
        if(!isPass){
            WebUtils.replyForFail(resp,checkReuslt.toString());
            return;
        }
        User user = new User();
        user.setId(userId);
        User queryOne = userService.queryOne(user);
        if (queryOne==null){
            return;
        }
        int issue = essayService.edit(title,summary.toString(), content,userId,queryOne.getUsername(),essayId);
        if (issue==0){
            WebUtils.replyForFail(resp,checkReuslt.toString());
            return;
        }
        WebUtils.replyForSuccess(resp);
        logger.info("文章修改成功");
    }

}


