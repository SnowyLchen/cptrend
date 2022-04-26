package com.cj.cptrend.colorseed.controller;

import com.cj.cptrend.lottery.domain.Lottery;
import com.cj.cptrend.lottery.service.ILotteryService;
import com.cj.cptrend.utils.controller.AjaxResult;
import com.cj.cptrend.utils.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/colorSeed")
public class ColorSeedController extends BaseController {

    private static final String PREFIX = "cp/colorseed/";

    @Resource
    ILotteryService lotteryService;


    @RequestMapping("/colorseed")
    public String colorSeed(ModelMap modelMap, Lottery lottery) throws ParseException {
        modelMap.put("cs", lotteryService.selectLotteryList(lottery));
        return PREFIX + "colorseed";
    }

    @RequestMapping("/colorseedforecast")
    public String colorSeed2(ModelMap modelMap, Lottery lottery) throws ParseException {
        modelMap.put("cs", lotteryService.selectLotteryList(lottery));
        return PREFIX + "colorseedforecast" + (lottery.getWeekend() != null ? "week" + lottery.getWeekend() : "");
    }

    @GetMapping("/getColorDataTable")
    @ResponseBody
    public AjaxResult getColorDataTable(Lottery lottery) throws ParseException {
        return AjaxResult.success(lotteryService.selectLotteryList(lottery));
    }

    @RequestMapping("/getColorseedData")
    @ResponseBody
    public List<Lottery> getColorseedData(Lottery lottery) throws ParseException {
        return lotteryService.selectLotteryList(lottery);
    }

    @RequestMapping("/select")
    public String select(ModelMap modelMap) {
        return "cp/fixed/selectfixed";
    }


    @RequestMapping("/getData")
    @ResponseBody
    public Integer insertColorSeed(ModelMap modelMap) throws ParseException {
        lotteryService.getNewData();
        return 1;
    }


}
