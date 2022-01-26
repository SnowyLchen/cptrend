package com.cj.cptrend.utils.constants;

/**
 * 显示条数枚举
 */
public enum PageSizeConstant {
    PAGE_SIZE_CONSTANT_20(1, 0, 20),
    PAGE_SIZE_CONSTANT_50(2, 0, 50),
    PAGE_SIZE_CONSTANT_100(3, 0, 100),
    PAGE_SIZE_CONSTANT_200(4, 0, 200),
    PAGE_SIZE_CONSTANT_0(0, 0, 1000000);
    private Integer key;
    private Integer start;
    private Integer end;

    PageSizeConstant(Integer key, Integer start, Integer end) {
        this.key = key;
        this.start = start;
        this.end = end;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
