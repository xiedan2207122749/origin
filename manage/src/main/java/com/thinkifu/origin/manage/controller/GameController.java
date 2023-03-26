package com.thinkifu.origin.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.thinkifu.origin.commons.entity.*;
import com.thinkifu.origin.commons.util.Result;
import com.thinkifu.origin.manage.form.SaveGameDictForm;
import com.thinkifu.origin.manage.form.SaveGameForm;
import com.thinkifu.origin.manage.form.SaveGameImageForm;
import com.thinkifu.origin.manage.form.SearchGameForm;
import com.thinkifu.origin.manage.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


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
    @Autowired
    private GameDictService gameDictService;
    @Autowired
    private GameSiteService gameSiteService;
    @Autowired
    private GameImageService gameImageService;
    @Autowired
    private GameDetailService gameDetailService;

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

    @GetMapping("/getDetail")
    @ApiOperation("获取游戏详情")
    public Result<GameEntity> getDetail(@RequestParam Long gameId) {
        GameEntity gameEntity = gameService.getOne(new QueryWrapper<GameEntity>().select("id,name,english_name,release_date,release_company,language,age_division,alias,platform,introduce_image,introduction,content_description,file_size,type,recommend_flag")
            .eq("id", gameId).last("limit 1")
        );
        GameDetailEntity gameDetailEntity = gameDetailService.getOne(new QueryWrapper<GameDetailEntity>().select("introduction, content_description").eq("id", gameId).last("limit 1"));
        gameEntity.setIntroduction(gameDetailEntity.getIntroduction());
        gameDetailEntity.setContentDescription(gameDetailEntity.getContentDescription());
        return Result.ok(gameEntity);
    }

    @GetMapping("/getGameIdAndName")
    @ApiOperation("获取游戏id和名称(添加轮播图的时候获取下拉框的值)")
    public Result<List<GameEntity>> getGameIdAndName() {
        return Result.ok(gameService.list(new QueryWrapper<GameEntity>().select("id,name").orderByDesc("id")));
    }

    @GetMapping("/update")
    @ApiOperation("修改游戏基本信息")
    public Result update(@RequestBody SaveGameForm form) {
        gameService.update(form);
        return Result.ok();
    }

    @GetMapping("/getGameDictId")
    @ApiOperation("获取游戏字典id")
    public Result<List<Long>> getGameDictId(@RequestParam Long gameId) {
        return Result.ok(gameDictService.listObjs(new QueryWrapper<GameDictEntity>().select("dict_id").eq("game_id", gameId), item -> (long) item));
    }

    @PostMapping("/updateGameDict")
    @ApiOperation("修改游戏字典")
    public Result updateGameDict(@RequestBody SaveGameDictForm form) {
        gameDictService.update(form);
        return Result.ok();
    }

    @GetMapping("/getGameSite")
    @ApiOperation("获取游戏位置")
    public Result<List<GameSiteEntity>> getGameSite(@RequestParam Long gameId) {
        return Result.ok(gameSiteService.list(new QueryWrapper<GameSiteEntity>().select("platform, link, id").eq("game_id", gameId)));
    }

    @PostMapping("/saveGameSite")
    @ApiOperation("修改游戏字典")
    public Result updateGameDict(@RequestBody GameSiteEntity entity) {
        gameSiteService.saveOrUpdate(entity);
        return Result.ok();
    }

    @PostMapping("/deleteGameSite/{gameSiteId}")
    @ApiOperation("删除游戏位置")
    public Result deleteGameSite(@PathVariable Long gameSiteId) {
        gameSiteService.removeById(gameSiteId);
        return Result.ok();
    }

    @GetMapping("/getGameImage")
    @ApiOperation("获取游戏图片")
    public Result<List<String>> getGameImage(@RequestParam Long gameId) {
        return Result.ok(gameImageService.listObjs(new QueryWrapper<GameImageEntity>().select("image").eq("game_id", gameId), Objects::toString));
    }

    @PostMapping("/saveGameImage")
    @ApiOperation("保存游戏图片")
    public Result saveGameImage(@RequestBody SaveGameImageForm form) {
        gameImageService.update(form);
        return Result.ok();
    }
}
