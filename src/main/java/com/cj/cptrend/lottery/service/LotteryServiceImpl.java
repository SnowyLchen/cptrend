package com.cj.cptrend.lottery.service;

import com.cj.cptrend.lottery.domain.Lottery;
import com.cj.cptrend.lottery.mapper.LotteryReadMapper;
import com.cj.cptrend.lottery.mapper.LotteryWriteMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhuru
 */
@Service("iLotteryServiceImpl")
public class LotteryServiceImpl implements ILotteryService {

    @Resource
    LotteryReadMapper mLotteryReadMapper;

    @Resource
    LotteryWriteMapper mLotteryWriteMapper;

    @Override
    public Lottery selectLotteryByPrivateKey(int numPeriods) {
        return mLotteryReadMapper.selectLotteryByPrivateKey(numPeriods);
    }

    @Override
    public List<Lottery> selectLotteryList(Lottery lottery) {
        return mLotteryReadMapper.selectLotteryList(lottery);
    }

    @Override
    public int insertLottery(Lottery lottery) {
        return mLotteryWriteMapper.insertLottery(lottery);
    }

    @Override
    public int updateLottery(Lottery lottery) {
        return mLotteryWriteMapper.updateLottery(lottery);
    }

    @Override
    public int deleteLottery(int numPeriods) {
        return mLotteryWriteMapper.deleteLottery(numPeriods);
    }

}
