package com.thinkifu.origin.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thinkifu.origin.commons.entity.GameDetailEntity;
import com.thinkifu.origin.manage.dao.GameDetailDao;
import com.thinkifu.origin.manage.service.GameDetailService;
import org.springframework.stereotype.Service;

/**
 * 游戏详情表
 *
 * @author Mr_xie
 * @email 15973488008@163.com
 * @date 2023-03-26 19:18:10
 */

@Service("gameDetailService")
public class GameDetailServiceImpl extends ServiceImpl<GameDetailDao, GameDetailEntity> implements GameDetailService {

}
