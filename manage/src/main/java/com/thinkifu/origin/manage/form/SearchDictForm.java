package com.thinkifu.origin.manage.form;

import com.thinkifu.origin.commons.form.PageForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xieDan
 * @Classname SearchDictForm
 * @Description 查询字典表form
 * @Date 2023/3/24 23:02
 * @Created by mr_xie
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("查询字典表form")
public class SearchDictForm extends PageForm {

    /**
     * 值
     */
    @ApiModelProperty("值")
    private String value;
    /**
     * 1. 电脑游戏 2.安卓游戏 3. 免费学习
     */
    @ApiModelProperty("1. 电脑游戏 2.安卓游戏 3. 免费学习")
    private Integer type;
}
