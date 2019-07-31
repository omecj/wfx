package com.qf.test;

import com.qf.entity.dto.AdminModule;
import com.qf.mapper.AdminViewObjectMapper;
import com.qf.service.impl.AdminModuleRoleServiceImpl;
import com.qf.service.impl.AdminServiceImpl;
import com.qf.service.impl.AdminViewObjectServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author-izumi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TService {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private AdminViewObjectMapper adminViewObjectMapper;

    @Autowired
    private AdminViewObjectServiceImpl adminViewObjectService;
    @Autowired
    private AdminModuleRoleServiceImpl adminModuleRoleService;

    @Test
    public void testMapper(){
        //System.out.println(adminViewObjectMapper.getModuleByAdminId(1));
        //System.out.println(adminViewObjectService.getModuleByAdminId(4L));
        List<AdminModule> permissionList = adminModuleRoleService.getPermissionList(1L);
        System.out.println(permissionList);

    }
}
