package com.letao.mall.controller.admin;


import com.letao.mall.service.StoreService;
import com.letao.mall.dao.entity.Store;
import com.letao.mall.vo.ErrorCode;
import com.letao.mall.vo.Result;
import com.letao.mall.vo.param.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 骑手反叛联盟
 * @since 2022-06-30
 */
@RestController
@RequestMapping("/mall/admin/store")
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;


    //修改店铺信息
    @RequestMapping("/modify")
    public Result modifyStoreAttributes(Store store) {
        if(storeService.getById(store) == null)
            return Result.fail(ErrorCode.STORE_NOT_EXIST.getCode(), ErrorCode.STORE_NOT_EXIST.getMsg());
        return Result.success(storeService.updateById(store));
    }


    //根据id删除店铺
    @GetMapping("/delete")
    public Result deleteStore(@PathVariable Long id) {
        if(storeService.getById(id) == null)
            return Result.fail(ErrorCode.STORE_NOT_EXIST.getCode(), ErrorCode.STORE_NOT_EXIST.getMsg());
        return Result.success(storeService.removeById(id));
    }

    //添加门店
    @RequestMapping("/add")
    public Result addStore(@RequestBody Store store) {
        if(storeService.getById(store) != null)
            return Result.fail(ErrorCode.STORE_EXIST.getCode(), ErrorCode.STORE_NOT_EXIST.getMsg());
        return Result.success(storeService.save(store));
    }

    //根据店铺id查询其店铺信息
    @PostMapping("/get")
    public Result getStore(PageParam pageParam) {

        return Result.success(storeService.showStore(pageParam));
    }

}

