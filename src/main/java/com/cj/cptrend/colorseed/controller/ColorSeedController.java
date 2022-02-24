package com.cj.cptrend.colorseed.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cj.cptrend.lottery.domain.Lottery;
import com.cj.cptrend.lottery.service.ILotteryService;
import com.cj.cptrend.utils.HttpUtils;
import com.cj.cptrend.utils.controller.AjaxResult;
import com.cj.cptrend.utils.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/colorSeed")
public class ColorSeedController extends BaseController {

    private static final String PREFIX = "cp/colorseed/";

    @Resource
    ILotteryService lotteryService;


    @RequestMapping("/colorseed")
    public String colorSeed(ModelMap modelMap, Lottery lottery) {
        modelMap.put("cs", lotteryService.selectLotteryList(lottery));
        return PREFIX + "colorseed";
    }

    @RequestMapping("/getColorDataTable")
    public AjaxResult getColorDataTable(Lottery lottery) {
        return AjaxResult.success(lotteryService.selectLotteryList(lottery));
    }

    @RequestMapping("/getColorseedData")
    @ResponseBody
    public List<Lottery> getColorseedData(Lottery lottery) {
        return lotteryService.selectLotteryList(lottery);
    }

    @RequestMapping("/select")
    public String select(ModelMap modelMap) {
        return "cp/fixed/selectfixed";
    }


    @RequestMapping("/getData")
    @ResponseBody
    public Integer insertColorSeed(ModelMap modelMap) {
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
            lotteryService.insertLottery(item);
        });
        return 1;
    }


}
