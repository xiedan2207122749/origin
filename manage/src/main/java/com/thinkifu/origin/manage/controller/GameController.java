package com.thinkifu.origin.manage.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thinkifu.origin.commons.entity.GameEntity;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.manage.form.SaveGameForm;
import com.thinkifu.origin.manage.form.SearchGameForm;
import com.thinkifu.origin.manage.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 游戏表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-25 10:40:05
 */
@Api(tags="游戏表")
@RestController
@Validated
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/list")
    @ApiOperation("查询游戏表列表")
    public Result<IPage<GameEntity>> list(SearchGameForm form) {
        return Result.ok(gameService.list(form));
    }

    @PostMapping("/add")
    @ApiOperation("添加游戏表")
    public Result add(@RequestBody SaveGameForm form) {
        gameService.add(form);
        return Result.ok();
    }

}
