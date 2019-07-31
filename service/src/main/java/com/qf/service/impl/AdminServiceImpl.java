package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.dto.Admin;
import com.qf.mapper.AdminMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author-izumi
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> {

    @Transactional(readOnly = true)
    public Admin getByUsername(String account) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Admin::getAccount,account);
        return this.getOne(wrapper);
    }
}
