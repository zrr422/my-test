package com.zrr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zrr.entity.AuditLog;
import com.zrr.entity.Company;
import com.zrr.uitl.Result;

import java.util.List;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 13:53
 * @ClassName: CompanyService
 * @Description: 企业实现层接口
 */
public interface CompanyService extends IService<Company> {
        /**
         *企业信息分页查询
         * @param companyName
         * @param page
         * @param limit
         * @return
         */
        IPage<Company> getCompany(String companyName, Integer page, Integer limit);

        /**
         * 查看单个企业认证详情
         * @param companyId
         * @return
         */
        Result<Company> getCompanyById(Long companyId);

        /**
         * 根据企业Id获取审核记录
         * @param companyId
         * @return
         */
        Result<List<AuditLog>> getAuditLogByCompanyId(Long companyId);

        /**
         *企业入驻
         * @param company
         * @return
         */
        Result<Company> insertCompany(Company company);

        /**
         *企业修改
         * @param company
         * @return
         */
        Result<Company> updateCompany(Company company);

        /**
         *企业审核
         * @param company
         * @return
         */
        Result<Company> auditPass(Company company);

        /**
         * 企业删除
         * @param companyId
         * @return
         */
        Result<Company> deleteCompany(Integer companyId);
}
