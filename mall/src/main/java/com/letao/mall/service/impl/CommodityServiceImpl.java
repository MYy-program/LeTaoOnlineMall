package com.letao.mall.service.impl;

import com.letao.mall.dao.entity.Commodity;
import com.letao.mall.dao.mapper.CommodityMapper;
import com.letao.mall.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 骑手反叛联盟
 * @since 2022-06-30
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;
    public int deleteCommodity(Integer id){
        return commodityMapper.deleteCommodity(id);
    }

    public int countAdminTotal(){
        return commodityMapper.countAdminTotal();
    }

    /**
     * 返回cnum>0的总数
     * @return
     */
    public int countCusTotal(){
        return commodityMapper.countCusTotal();
    }
}
