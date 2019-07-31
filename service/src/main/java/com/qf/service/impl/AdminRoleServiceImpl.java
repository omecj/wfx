package com.qf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qf.entity.dto.AdminModuleRole;
import com.qf.entity.dto.AdminRole;
import com.qf.mapper.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author-izumi
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> {
    @Autowired
    private AdminModuleRoleServiceImpl adminModuleRoleService;

    @Transactional
    public void saveRole(AdminRole adminRole, String moduleChecked) {
        this.saveOrUpdate(adminRole);
        QueryWrapper<AdminModuleRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AdminModuleRole::getAdminRoleId, adminRole.getId());
        adminModuleRoleService.remove(wrapper);
        String[] split = moduleChecked.split("\\|");
        int len = split.length;
        List<AdminModuleRole> insert = new ArrayList<>(len);    //优化：提前给定长度，避免list自动扩容
        for (int i = 0; i < len; i++) {
            if (!"|".equals(split[i])&&!"".equals(split[i])) {
                AdminModuleRole adminModuleRole = new AdminModuleRole();
                adminModuleRole.setAdminModuleId(Long.parseLong(split[i]));
                adminModuleRole.setAdminRoleId(adminRole.getId());
                insert.add(adminModuleRole);
            }
        }
        if (insert.size() > 0) {
            adminModuleRoleService.saveBatch(insert);
        }
    }
    @Transactional
    public void removeRoleById(Long id) {
        this.removeById(id);
        QueryWrapper<AdminModuleRole> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(AdminModuleRole::getAdminRoleId,id);
        adminModuleRoleService.remove(wrapper);
    }
}
