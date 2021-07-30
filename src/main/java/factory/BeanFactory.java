package factory;

import controller.BaseController;
import dao.impl.UserDaoImpl;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pojo.User;

import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tptogiar
 * @descripiton 用户实例化指定的JavaBean
 * @create 2021/07/22-15:57
 */


public class BeanFactory {

    public static HashMap<Class,Object> beanPathMap =new HashMap(16);

    /**
     * 实例化传进来的类
     * 如果此类没有被实例化过，则执行实例化
     * 如果此类已经被实例化过，则直接返回上一次实例化的结果
     * @param bean
     * @param <E>
     * @return
     */
    public static <E> E getBeanIntance(Class<E> bean){
        Object instance = beanPathMap.get(bean);
        if (instance==null){
            E newInstance = null;
            try {
                newInstance = bean.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            beanPathMap.put(bean,newInstance);
            return newInstance;
        }
        return (E)instance;
    }
}
