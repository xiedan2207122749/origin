package com.thinkifu.origin.manage.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xieDan
 * @Classname SaveGameImageForm
 * @Description 修改游戏图片form
 * @Date 2023/3/25 18:32
 * @Created by mr_xie
 */
@Data
@ApiModel("修改游戏图片form")
public class SaveGameImageForm {

    @ApiModelProperty("图片List")
    private List<String> imageList;

    @ApiModelProperty("游戏id")
    private Long gameId;
}
