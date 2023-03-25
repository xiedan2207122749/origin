package com.thinkifu.origin.commons.form;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author xieDan
 * @Classname PageUtil
 * @Description
 * @Date 2021/3/31 14:54
 * @Created by mr_xie
 */
@Data
@ApiModel("分页基类")
public class PageForm {

    /**
     * 分页尺寸
     */
    @Min(value = 5, message = "分页尺寸不在取值范围内")
    @ApiModelProperty("分页尺寸")
    private Integer pageSize = 10;

    /**
     * 当前页
     */
    @Min(value = 1, message = "当前页不在取值范围内")
    @ApiModelProperty("当前页")
    private Integer currPage = 1;

    @JsonIgnore
    public <T>IPage<T> getPage() {
        return new Page<T>(currPage, pageSize);
    }

}
