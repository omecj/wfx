package com.qf.mapper;

import com.qf.entity.vo.JqueryTreeView;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author-izumi
 */
@Repository
public interface AdminViewObjectMapper{

    List<JqueryTreeView> getModuleByAdminId(@Param("adminId") Long adminId);

}
