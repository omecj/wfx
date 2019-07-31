package com.qf.service.impl;

import com.qf.entity.vo.JqueryTreeView;
import com.qf.mapper.AdminViewObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author-izumi
 */
@Service
public class AdminViewObjectServiceImpl{
    @Autowired
    private AdminViewObjectMapper adminViewObjectMapper;

    @Transactional(readOnly = true)
    public List<JqueryTreeView> getModuleByAdminId(Long adminId){
        return adminViewObjectMapper.getModuleByAdminId(adminId);
    }
}
