package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.dto.MerchantUser;
import com.qf.mapper.MerchantUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author-izumi
 */
@Service
public class MerchantUserServiceImpl extends ServiceImpl<MerchantUserMapper, MerchantUser> {

    @Transactional(readOnly = true)
    public MerchantUser getByUsername(String username) {
        QueryWrapper<MerchantUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(MerchantUser::getUserName, username);
        return this.getOne(wrapper);
    }
}
