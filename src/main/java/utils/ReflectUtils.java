package utils;

import annotation.DbField;
import annotation.IndirectDbField;
import pojo.User;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Tptogiar
 * @descripiton
 * @create 2021/07/22-17:55
 */


public class ReflectUtils {


    /**
     * 获取对象关于“type”的属性-->值键值对，并将这些键值对添加
     * 到dbFieldMap
     * @param object
     * @param dbFieldMap
     * @param type
     * @param <E>
     */
    public static <E> void getBeanDbFieldMap(E object, HashMap<String, Object> dbFieldMap, String type)  {
        try {
            Class clazz = object.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                DbField annotation = field.getAnnotation(DbField.class);
                if (annotation!=null){
                    if ("insert".equals(type)){
                        boolean insert = annotation.isInsert();
                        if (insert){
                            Object value = field.get(object);
                            dbFieldMap.put(annotation.fieldName(),value);
                        }
                    }else if ("update".equals(type)){
                        boolean update=annotation.isUpdate();
                        if (update){
                            Object value=field.get(object);
                            dbFieldMap.put(annotation.fieldName(),value);
                        }
                    }else if ("query".equals(type)){
                        boolean query = annotation.isQuery();
                        if (query){
                            Object value = field.get(object);
                            dbFieldMap.put(annotation.fieldName(),value);
                        }
                    }
                }
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把含有多组属性-->值键值对的fieldValueMap添加到一个对象bean中
     * @param fieldValueMap
     * @param bean
     * @param <E>
     * @return
     */
    public static  <E> E addFieldToBean(Map<String, Object> fieldValueMap, E bean){
        try {
            Class clazz = bean.getClass();
            ArrayList<Field> fields = new ArrayList<>();
            while (clazz!=null){
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
                clazz=clazz.getSuperclass();
            }
            for (Field field:fields) {
                DbField dbFieldAnotation = field.getAnnotation(DbField.class);
                IndirectDbField indirectDbFieldAnotation = field.getAnnotation(IndirectDbField.class);
                //该属性是该类对应的表中的字段
                if (dbFieldAnotation!=null && fieldValueMap.containsKey(dbFieldAnotation.fieldName())){
                    field.setAccessible(true);
                    field.set(bean,fieldValueMap.get(dbFieldAnotation.fieldName()));
                }
                //该属性不是该类对应的表中的字段，来自其他表
                if (indirectDbFieldAnotation!=null && fieldValueMap.containsKey(indirectDbFieldAnotation.fieldName())){
                    field.setAccessible(true);
                    field.set(bean,fieldValueMap.get(indirectDbFieldAnotation.fieldName()));
                }
            }
            return bean;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把多个含有多组属性-->值键值对的fieldValueMap分别添加到多个对象bean中
     * @param mapList
     * @param bean
     * @param <E>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <E> List<E> addFieldToBeans(List<Map<String, Object>> mapList, E bean) throws IllegalAccessException, InstantiationException {
        ArrayList<E> beans = new ArrayList<>();
        for (Map<String,Object> fieldValueMap:mapList) {
            E newInstance = (E) bean.getClass().newInstance();
            E newBean = addFieldToBean(fieldValueMap, newInstance);
            beans.add(newBean);
        }
        return beans;
    }
}
