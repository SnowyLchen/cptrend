package com.cj.cptrend.lottery.mapper;

import com.cj.cptrend.lottery.domain.Lottery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据库对象
 *
 * @author chen
 */
@Mapper
public interface LotteryWriteMapper {

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
     * @param numPeriods 主键ID
     * @return 返回0删除失败，大于0删除成功
     */
    int deleteLottery(int numPeriods);

}
