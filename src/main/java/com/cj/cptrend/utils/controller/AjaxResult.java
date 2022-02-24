package com.cj.cptrend.utils.controller;

import lombok.Data;

import java.util.List;

@Data
public class AjaxResult {
    private Object code;
    private Object msg;
    private Object count;
    private Object data;

    public static AjaxResult success(List<?> list) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.code = 200;
        ajaxResult.msg = "成功";
        ajaxResult.count = list.size();
        ajaxResult.data = list;
        return ajaxResult;
    }
}
