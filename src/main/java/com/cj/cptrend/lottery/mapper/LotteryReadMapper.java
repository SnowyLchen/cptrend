package com.cj.cptrend.lottery.mapper;

import com.cj.cptrend.lottery.domain.Lottery;

import java.util.List;

/**
 * 数据库对象
 *
 * @author chen
 */
public interface LotteryReadMapper {

    /**
     * 根据主键ID查询
     *
     * @param numPeriods 表主键ID
     * @return 信息
     */
    Lottery selectLotteryByPrivateKey(int numPeriods);

    /**
     * 查询最新的一条
     *
     * @return 信息
     */
    Lottery selectLotteryByNew();

    /**
     * 查询符合条件的列表，查询条件为空时加载所有
     *
     * @param lottery
     * @return 列表
     */
    List<Lottery> selectLotteryList(Lottery lottery);

}
