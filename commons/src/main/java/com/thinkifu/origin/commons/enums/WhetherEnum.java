package com.thinkifu.origin.commons.enums;

import lombok.Getter;

/**
 * @author xieDan
 * @Classname WhetherEnum
 * @Description 是否枚举
 * @Date 2022/8/23 17:08
 * @Created by mr_xie
 */
@Getter
public enum WhetherEnum {
    YES(1),
    NO(0),
    ;
    
    
    private final int value;
    
    
    WhetherEnum(int value) {
        this.value = value;
    }
}
