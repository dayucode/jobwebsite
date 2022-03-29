package com.pearadmin.pro.common.web.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * Base Service
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/03/27
 * */
public abstract class BaseService<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> {

}
