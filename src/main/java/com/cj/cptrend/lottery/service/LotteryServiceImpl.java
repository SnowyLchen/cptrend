package com.cj.cptrend.lottery.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cj.cptrend.lottery.domain.Lottery;
import com.cj.cptrend.lottery.mapper.LotteryReadMapper;
import com.cj.cptrend.lottery.mapper.LotteryWriteMapper;
import com.cj.cptrend.utils.DateUtils;
import com.cj.cptrend.utils.HttpUtils;
import com.cj.cptrend.utils.constants.PageSizeConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Lottery> selectLotteryList(Lottery lottery) throws ParseException {
//        updateNum();
        getNewData();
        for (PageSizeConstant value : PageSizeConstant.values()) {
            if (value.getKey().equals(lottery.getCurrPageSize())) {
                lottery.setPageNum(value.getStart());
                lottery.setPageSize(value.getEnd());
                break;
            } else if (lottery.getCurrPageSize() == null) {
                lottery.setPageNum(0);
                lottery.setPageSize(20);
                break;
            }
        }
        List<Lottery> lotteries = mLotteryReadMapper.selectLotteryList(lottery);
        return lotteries.stream().sorted(Comparator.comparing(Lottery::getNumPeriods)).collect(Collectors.toList());
    }

    private void updateNum() {
        Lottery lottery = mLotteryReadMapper.selectLotteryByNew();
        if (lottery == null) {
            return;
        }
        String url = "http://150.158.80.116:1000/lot_500_dlt.txt";
        String data = HttpUtils.doGet(url, null);
        List<String> all = Arrays.asList(data.split("\n"));
        List<Lottery> lotteries = new ArrayList<>();
        all.parallelStream().forEach(item -> {
            String num = item.split(":")[1];
            String[] split = num.split(",");
            for (int i = 0; i < split.length; i++) {
                if (i > 0 && Integer.parseInt(split[0]) > lottery.getNumPeriods()) {
                    Lottery lt = new Lottery();
                    lt.setNumPeriods(Integer.parseInt(split[0]));
                    lt.setPreNum1(split[1]);
                    lt.setPreNum2(split[2]);
                    lt.setPreNum3(split[3]);
                    lt.setPreNum4(split[4]);
                    lt.setPreNum5(split[5]);
                    lt.setSufNum1(split[6]);
                    lt.setSufNum2(split[7]);
                    lt.setDate(item.split(":")[0]);
                    lotteries.add(lt);
                    break;
                }
            }
        });
        lotteries.parallelStream().forEach(this::insertLottery);
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

    @Override
    public int getNewData() throws ParseException {
        Lottery lotteryLast = mLotteryReadMapper.selectLotteryByNew();
        Calendar calendar = Calendar.getInstance();
        if (lotteryLast == null || DateUtils.calcDateNums(lotteryLast.getDate(), DateUtils.getCurrentDate()) < 2) {
            return 0;
        }
        String data = HttpUtils.doGet("https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=10000&isVerify=1&pageNo=1", null);
        JSONObject jsonObject = JSON.parseObject(data);
        String string = JSON.parseObject(jsonObject.getString("value")).getString("list");
        List<JSONObject> jsonObjects = JSON.parseArray(string, JSONObject.class);
        List<Lottery> lotteries = new ArrayList<>();
        for (JSONObject object : jsonObjects) {
            try {
                Lottery lottery = new Lottery();
                String lotteryDrawNum = object.getString("lotteryDrawNum");
                lottery.setNumPeriods(Integer.valueOf(lotteryDrawNum));
//                String pre = object.getString("lotteryUnsortDrawresult").split(" ")[0];
//                String suf = object.getString("lotteryUnsortDrawresult").split(" ")[1];
                String[] d = object.getString("lotteryDrawResult").split(" ");
                lottery.setPreNum1(d[0]);
                lottery.setPreNum2(d[1]);
                lottery.setPreNum3(d[2]);
                lottery.setPreNum4(d[3]);
                lottery.setPreNum5(d[4]);
                lottery.setSufNum1(d[5]);
                lottery.setSufNum2(d[6]);
                lottery.setDate(object.getString("lotteryDrawTime"));
                lotteries.add(lottery);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        lotteries.parallelStream().forEach(item -> {
            if (item.getNumPeriods() > lotteryLast.getNumPeriods()) {
                insertLottery(item);
            }
        });
        return 1;
    }

}
