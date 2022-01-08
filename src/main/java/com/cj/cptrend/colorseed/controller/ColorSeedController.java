package com.cj.cptrend.colorseed.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cj.cptrend.lottery.domain.Lottery;
import com.cj.cptrend.lottery.service.ILotteryService;
import com.cj.cptrend.utils.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/colorSeed")
public class ColorSeedController {

    private static final String PREFIX = "cp/colorseed/";

    @Resource
    ILotteryService lotteryService;


    @RequestMapping("/colorseed")
    public String colorSeed(ModelMap modelMap) {
        modelMap.put("cs", lotteryService.selectLotteryList(new Lottery()));
        return PREFIX + "colorseed";
    }

    public static void main(String[] args) {
        String data = HttpUtils.doPost("https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=100&isVerify=1&pageNo=1", new StringBuilder());
        JSONObject jsonObject = JSON.parseObject(data);
        String string = JSON.parseObject(jsonObject.getString("value")).getString("list");
        List<JSONObject> jsonObjects = JSON.parseArray(string, JSONObject.class);
        for (JSONObject object : jsonObjects) {
            Lottery lottery = new Lottery();
            String lotteryDrawNum = object.getString("lotteryDrawNum");
            lottery.setNumPeriods(Integer.valueOf(lotteryDrawNum));
            String pre = object.getString("lotteryUnsortDrawresult").split(" ")[0];
            String suf = object.getString("lotteryUnsortDrawresult").split(" ")[1];
            String[] preNum = pre.split("\\+");
            lottery.setPreNum1(preNum[0]);
            lottery.setPreNum2(preNum[1]);
            lottery.setPreNum3(preNum[2]);
            lottery.setPreNum4(preNum[3]);
            lottery.setPreNum5(preNum[4]);
            String[] sufNum = suf.split("\\+");
            lottery.setSufNum1(sufNum[0]);
            lottery.setSufNum2(sufNum[1]);
        }
    }
}
