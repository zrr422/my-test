package com.zrr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zrr.entity.AuditLog;
import com.zrr.entity.Company;
import com.zrr.service.CompanyService;
import com.zrr.uitl.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 14:20
 * @ClassName: CompanyController
 * @Description: 企业控制层
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Resource
    CompanyService service;

    /**
     * 企业分页查询
     * @param companyName
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getCompany")
    public Object getCompany(String companyName,@RequestParam Integer page,@RequestParam Integer limit){
        IPage<Company> ipage = service.getCompany(companyName,page,limit);
        List<Company> data = ipage.getRecords(); // 当前页的数据
        int total = (int) ipage.getTotal();
        return Result.success("查询成功",data,total);

    }

    /**
     * 根据企业ID查看企业详细信息
     * @param companyId
     * @return
     */
    @GetMapping("/getCompanyById")
    public Result<Company> getCompanyById(Long companyId) {
        return service.getCompanyById(companyId);
    }

    /**
     * 企业入驻
     * @param company
     * @return
     */
    @PostMapping("/insertCompany")
    public Result<Company> insertCompany(@Valid @RequestBody Company company) {
        return service.insertCompany(company);
    }

    /**
     * 修改企业
     * @param company
     * @return
     */
    @PostMapping("/updateCompany")
    public Result<Company> updateCompany(@Valid @RequestBody Company company) {
        return service.updateCompany(company);
    }

    /**
     * 删除企业
     * @param companyId
     * @return
     */
    @PostMapping("/deleteCompany")
    public Result<Company> deleteCompany(Integer companyId) {
        return service.deleteCompany(companyId);
    }

    /**
     * 根据企业ID获取审核记录
     * @param companyId
     * @return
     */
    @GetMapping("/getAuditLogByCompanyId")
    public Result<List<AuditLog>> getAuditLogByCompanyId(Long companyId) {
        return service.getAuditLogByCompanyId(companyId);
    }

    /**
     * 企业审核
     * @param company
     * @return
     */
    @PostMapping("/auditPass")
    public Result<Company> auditPass(@Valid @RequestBody Company company) {
        return service.auditPass(company);
    }
}
