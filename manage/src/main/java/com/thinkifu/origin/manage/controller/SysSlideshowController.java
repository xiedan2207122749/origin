package com.thinkifu.origin.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thinkifu.origin.commons.entity.SlideshowEntity;
import com.thinkifu.origin.commons.form.PageForm;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.manage.form.SaveSlideshowForm;
import com.thinkifu.origin.manage.service.SlideshowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author xieDan
 * @Classname SlideshowController
 * @Description 轮播图接口
 * @Date 2022/11/6 18:40
 * @Created by mr_xie
 */
@Api(tags="轮播图接口")
@RestController
@RequestMapping("/sys/slideshow")
public class SysSlideshowController {

    @Autowired
    private SlideshowService slideshowService;

    @PostMapping("/updateOrSave")
    @ApiOperation("添加或者修改轮播图")
    public Result updateOrSave(@RequestBody SaveSlideshowForm form) {
        slideshowService.updateOrSave(form);
        return Result.ok();
    }

    @PostMapping("/pageCommodity")
    @ApiOperation("获取轮播图分页数据")
    public Result<IPage<SlideshowEntity>> pageCommodity(@RequestBody @Valid PageForm form) {
        IPage<SlideshowEntity> page = form.getPage();
        return Result.ok(slideshowService.page(page, new QueryWrapper<SlideshowEntity>().select("id, image, order_num, create_time").orderByAsc("order_num")));
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除轮播")
    public Result delete(@PathVariable Long id) {
        slideshowService.delete(id);
        return Result.ok();
    }
}
