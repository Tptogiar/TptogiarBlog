package service;

import utils.JedisUtils;

public interface JedisService {

    Integer updateRate = JedisUtils.updateRate;

    /**
     * 计数，并判断是否到达需要向mysql写入的阀值
     */
    void countForUpdateToDb();

    /**
     * 将redis中关于浏览量的集合更新到mysql中
     */
    void updateToDb();







}
