package com.cj.cptrend.lottery.domain;

/**
 * @author chen
 */
public class Lottery implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer numPeriods;

    /**
     *
     */
    private String preNum1;

    /**
     *
     */
    private String preNum2;

    /**
     *
     */
    private String preNum3;

    /**
     *
     */
    private String preNum4;

    /**
     *
     */
    private String preNum5;

    /**
     *
     */
    private String sufNum1;

    /**
     *
     */
    private String sufNum2;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getNumPeriods() {
        return numPeriods;
    }

    public void setNumPeriods(Integer numPeriods) {
        this.numPeriods = numPeriods;
    }

    public String getPreNum1() {
        return preNum1;
    }

    public void setPreNum1(String preNum1) {
        this.preNum1 = preNum1;
    }

    public String getPreNum2() {
        return preNum2;
    }

    public void setPreNum2(String preNum2) {
        this.preNum2 = preNum2;
    }

    public String getPreNum3() {
        return preNum3;
    }

    public void setPreNum3(String preNum3) {
        this.preNum3 = preNum3;
    }

    public String getPreNum4() {
        return preNum4;
    }

    public void setPreNum4(String preNum4) {
        this.preNum4 = preNum4;
    }

    public String getPreNum5() {
        return preNum5;
    }

    public void setPreNum5(String preNum5) {
        this.preNum5 = preNum5;
    }

    public String getSufNum1() {
        return sufNum1;
    }

    public void setSufNum1(String sufNum1) {
        this.sufNum1 = sufNum1;
    }

    public String getSufNum2() {
        return sufNum2;
    }

    public void setSufNum2(String sufNum2) {
        this.sufNum2 = sufNum2;
    }

}
