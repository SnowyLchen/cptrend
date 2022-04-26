package com.cj.cptrend.lottery.service;

import com.cj.cptrend.lottery.domain.Lottery;

import java.text.ParseException;
import java.util.List;

/**
 * 数据库对象
 *
 * @author chen
 */
public interface ILotteryService {

    /**
     * 根据主键ID查询
     *
     * @param numPeriods 表主键ID
     * @return 信息
     */
    Lottery selectLotteryByPrivateKey(int numPeriods);

    /**
     * 查询符合条件的列表，查询条件为空时加载所有
     *
     * @param lottery
     * @return 列表
     */
    List<Lottery> selectLotteryList(Lottery lottery) throws ParseException;

    /**
     * 保存到数据库
     *
     * @param lottery
     * @return 返回0新增失败，大于0新增成功
     */
    int insertLottery(Lottery lottery);

    /**
     * 编辑中的信息
     *
     * @param lottery
     * @return 返回0编辑失败，大于0编辑成功
     */
    int updateLottery(Lottery lottery);

    /**
     * 根据主键numPeriods删除信息
     *
     * @param numPeriods 表主键ID
     * @return 返回0删除失败，大于0删除成功
     */
    int deleteLottery(int numPeriods);

    /**
     * 获取最新数开奖结果
     * @return
     */
    int getNewData() throws ParseException;
}
