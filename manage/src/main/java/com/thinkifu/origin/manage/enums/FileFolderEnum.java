package com.thinkifu.origin.manage.enums;

import lombok.Getter;

/**
 * @author xieDan
 * @Classname FileFolderEnum
 * @Description 文件上传文件夹位置
 * @Date 2023/3/25 14:47
 * @Created by mr_xie
 */
@Getter
public enum FileFolderEnum {
    /** 电脑游戏介绍图 */
    COMPUTER_GAME_INTRODUCTION(1, "computerGame/introduction"),
    /** 电脑游戏详细图 */
    COMPUTER_GAME_DETAIL(2, "computerGame/detail"),
    /** 手游介绍图 */
    PHONE_GAME_INTRODUCTION(3,"phoneGame/introduction"),
    /** 手游详细图 */
    PHONE_GAME_DETAIL(4,"phoneGame/detail"),
    ;

    /** 文件位置 */
    private final String strValue;
    private final int value;

    FileFolderEnum(int value, String strValue) {
        this.value = value;
        this.strValue = strValue;
    }
}