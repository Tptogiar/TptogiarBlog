package controller;

import factory.BeanFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.CookiesUtils;
import utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/08/04-09:55
 */

@WebServlet(urlPatterns = "/image")
public class AvatarController extends BaseController{

    Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService = BeanFactory.getBeanIntance(UserServiceImpl.class);
    String realPath="";

    /**
     * 更新用户头像
     * @param req
     * @param resp
     * @throws Exception
     */
    protected void updateAvatar(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        boolean multipartContent = ServletFileUpload.isMultipartContent(req);
        if (!multipartContent){
            WebUtils.replyForFail(resp,"未监测到文件");
            return;
        }
        String username = CookiesUtils.getCookie("username", req.getCookies()).getValue();
        int userId = WebUtils.parseToInt(CookiesUtils.getCookie("userId", req.getCookies()).getValue(), null);
        User user = new User();
        user.setId(userId);
        User queryOne = userService.queryOne(user);
        if (queryOne==null){
            WebUtils.replyForFail(resp,"用户登录状态异常");
            return;
        }
        FileItem fileItem = null;

        fileItem = WebUtils.getUploadFileItem(req);

        if (fileItem.getSize()>1024*1024*8*3){
            WebUtils.replyForFail(resp,"文件大小不能超过3MB");
            return;
        }
        String[] split = fileItem.getName().split("\\.");
        String extensionName = split[split.length - 1];
        realPath=getServletContext().getRealPath("/");
        String avatarPath="/image/avatar/"+username+"_"+(new Date().getTime()) +"."+extensionName;
        File curAvatar = new File(realPath+avatarPath.substring(1));
        //不是默认头像的话删除原头像
        if (! "/image/avatar/logo.png".equals(user.getAvatarPath())){
            File pevrAvatar = new File(getServletContext().getRealPath(user.getAvatarPath()));
            pevrAvatar.delete();
        }
        try {
            fileItem.write(curAvatar);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("头像写入错误");
            throw new RuntimeException(e);
        }
        user.setAvatarPath(avatarPath);
        userService.updateOne(user);
        WebUtils.replyForSuccess(resp);
    }

}
