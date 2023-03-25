package com.thinkifu.origin.manage.form;

import com.thinkifu.origin.commons.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xieDan
 * @Classname SearchGameForm
 * @Description
 * @Date 2023/3/25 10:51
 * @Created by mr_xie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询游戏表form")
public class SearchGameForm extends PageForm {

    /**
     * 中文名称
     */
    @ApiModelProperty("中文名称")
    private String name;
    /**
     * 英文名称
     */
    @ApiModelProperty("英文名称")
    private String englishName;
    /**
     * 1.电脑游戏 2.手机游戏
     */
    @ApiModelProperty("1.电脑游戏 2.手机游戏")
    private Integer type;

    /** 字典id */
    @ApiModelProperty("字典id")
    private Long dictId;
}
