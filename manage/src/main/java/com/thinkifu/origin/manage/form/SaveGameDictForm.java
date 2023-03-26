package com.thinkifu.origin.manage.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xieDan
 * @Classname SaveGameDictForm
 * @Description 修改游戏字典form
 * @Date 2023/3/25 18:09
 * @Created by mr_xie
 */
@Data
@ApiModel("修改游戏字典form")
public class SaveGameDictForm {

    @ApiModelProperty("字典idList")
    private List<Long> dictIdList;

    @ApiModelProperty("游戏id")
    private Long gameId;
}
