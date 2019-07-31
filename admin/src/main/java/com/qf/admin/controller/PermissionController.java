package com.qf.admin.controller;

import com.qf.common.http.Result;
import com.qf.entity.dto.AdminModule;
import com.qf.entity.dto.AdminRole;
import com.qf.entity.po.LoginAdmin;
import com.qf.service.impl.AdminModuleRoleServiceImpl;
import com.qf.service.impl.AdminRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author-izumi
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private AdminRoleServiceImpl adminRoleService;
    @Autowired
    private AdminModuleRoleServiceImpl adminModuleRoleService;


    @RequestMapping("list")
    public String getPermissionList(Model model,LoginAdmin loginAdmin){
        model.addAttribute("roleList", adminRoleService.list());
        model.addAttribute("moduleList", adminModuleRoleService.getPermissionList(loginAdmin.getRoleId()));
        return "permission/list";
    }

    @PostMapping("save")
    @ResponseBody
    public Result<?> toSave(AdminRole adminRole,String moduleChecked){
        adminRoleService.saveRole(adminRole,moduleChecked);
        return Result.success();
    }

    @PostMapping("del")
    @ResponseBody
    public Result<?> toDel(@RequestParam(value = "id",required = true) Long id){
        adminRoleService.removeRoleById(id);
        return Result.success();
    }

    @PostMapping("getRoleById")
    @ResponseBody
    public Result<?> idToUser(@RequestParam(value = "id",required = true) Long id){
        aux aux = new aux();
        aux.setAdminRole(adminRoleService.getById(id));
        List<AdminModule> permissionList = adminModuleRoleService.getPermissionList(id);
        List<AdminModule> temp = new ArrayList<>();
        temp.add(new AdminModule(0L));
        aux.setAdminModuleList(permissionList==null? temp:permissionList);
        return Result.success(aux);
    }

    public class aux{
        private AdminRole adminRole;
        private List<AdminModule> adminModuleList;
        public AdminRole getAdminRole() {
            return adminRole;
        }
        public void setAdminRole(AdminRole adminRole) {
            this.adminRole = adminRole;
        }
        public List<AdminModule> getAdminModuleList() {
            return adminModuleList;
        }
        public void setAdminModuleList(List<AdminModule> adminModuleList) {
            this.adminModuleList = adminModuleList;
        }
    }
}
