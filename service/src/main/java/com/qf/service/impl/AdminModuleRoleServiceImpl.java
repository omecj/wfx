package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.dto.AdminModule;
import com.qf.entity.dto.AdminModuleRole;
import com.qf.mapper.AdminModuleRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author-izumi
 */
@Service
public class AdminModuleRoleServiceImpl extends ServiceImpl<AdminModuleRoleMapper, AdminModuleRole> {

    @Autowired
    private AdminModuleServiceImpl adminModuleService;
    //根据roleId查询当前role所拥有的权限
    @Transactional(readOnly = true)
    public List<AdminModule> getPermissionList(Long roleId){
        QueryWrapper<AdminModuleRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AdminModuleRole::getAdminRoleId,roleId);
        List<AdminModuleRole> list = this.list(wrapper);    //根据当前roleId获取模块id
        if (list.size()==0){
            return null;
        }
        List<Long> ids = new ArrayList<>();
        for (AdminModuleRole adminModuleRole : list) {
            Long adminModuleId = adminModuleRole.getAdminModuleId();
            ids.add(adminModuleId);
        }
        Collection<AdminModule> adminModules = adminModuleService.listByIds(ids);   //根据模块id查询模块名称
        return (List<AdminModule>) adminModules;
    }

}
