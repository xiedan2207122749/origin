package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.DictEntity;
import com.thinkifu.origin.manage.dao.DictDao;
import com.thinkifu.origin.manage.service.DictService;
import org.springframework.stereotype.Service;

/**
 * 类型表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-24 22:52:31
 */

@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictDao, DictEntity> implements DictService {

}
