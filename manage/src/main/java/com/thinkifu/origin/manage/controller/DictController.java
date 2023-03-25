package com.thinkifu.origin.manage.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thinkifu.origin.commons.entity.DictEntity;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.manage.form.SearchDictForm;
import com.thinkifu.origin.manage.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 类型表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-24 22:52:31
 */
@Api(tags="类型表")
@RestController
@Validated
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @GetMapping("/list")
    @ApiOperation("查询字典表列表")
    public Result<IPage<DictEntity>> list(SearchDictForm form) {
        IPage<DictEntity> page = form.getPage();
        dictService.page(page, new QueryWrapper<DictEntity>()
                .select("id, value, type, create_time")
                .like(StrUtil.isNotBlank(form.getValue()), "value", form.getValue())
                .eq(form.getType() != null, "type", form.getType())
                .orderByDesc("id")
        );
        return Result.ok(page);
    }

    @GetMapping("/listByType")
    @ApiOperation("查询字典表列表通过类型")
    public Result<List<DictEntity>> list(@RequestParam Integer type) {
        return Result.ok(dictService.list(new QueryWrapper<DictEntity>().select("id, value").eq( "type", type).orderByDesc("id")));
    }

    /**
     * 保存商家信息
     */
    @PostMapping("/save")
    @ApiOperation("保存商家信息")
    public Result save(@RequestBody DictEntity entity) {
        dictService.saveOrUpdate(entity);
        return Result.ok();
    }

    /**
     * 删除商家信息
     */
    @PostMapping("/delete/{id}")
    @ApiOperation("删除商家信息")
    public Result delete(@PathVariable Long id) {
        dictService.removeById(id);
        return Result.ok();
    }
}
