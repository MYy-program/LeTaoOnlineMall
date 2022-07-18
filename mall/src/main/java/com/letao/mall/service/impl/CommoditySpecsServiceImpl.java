package com.letao.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.letao.mall.dao.entity.CommoditySpecs;
import com.letao.mall.dao.mapper.CommoditySpecsMapper;
import com.letao.mall.service.CommodityService;
import com.letao.mall.service.CommoditySpecsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.letao.mall.util.PicUtils;
import com.letao.mall.vo.CommodityVo;
import com.letao.mall.vo.ErrorCode;
import com.letao.mall.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 骑手反叛联盟
 * @since 2022-06-30
 */
@Service
public class CommoditySpecsServiceImpl extends ServiceImpl<CommoditySpecsMapper, CommoditySpecs> implements CommoditySpecsService {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private PicUtils picUtils;

    @Override
    public Result getSpecsByCid(Long cid) {
        List<CommoditySpecs> list = new ArrayList<>();
        list = this.list(new LambdaQueryWrapper<CommoditySpecs>().eq(CommoditySpecs::getCid,cid));
        if(list!=null&&list.size()!=0){
            return Result.success(list);
        }else{
            return Result.fail(ErrorCode.SEARCH_ERROR.getCode(),ErrorCode.SEARCH_ERROR.getMsg());
        }
    }

    @Override
    public Result getPic(Long cid) throws IOException {
        List<String> list = new ArrayList<>();
        list.add(commodityService.getById(cid).getCpicture());
        List<CommoditySpecs> temp;
        temp = this.list(new LambdaQueryWrapper<CommoditySpecs>().eq(CommoditySpecs::getCid,cid));
        for (int i = 0; i < temp.size(); i++) {
            list.add(temp.get(i).getCpicture());
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String imageUrl = list.get(i);
            String data =picUtils.encrypt(imageUrl);
            result.add(data);
        }
        return Result.success(result);
    }
}
