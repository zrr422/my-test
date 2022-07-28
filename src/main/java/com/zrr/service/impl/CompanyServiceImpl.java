package com.zrr.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zrr.entity.AuditLog;
import com.zrr.entity.Company;
import com.zrr.entity.Student;
import com.zrr.mapper.AuditLogMapper;
import com.zrr.mapper.CompanyMapper;
import com.zrr.service.CompanyService;
import com.zrr.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Author zhangrongrong
 * @Date 2022/7/26 13:54
 * @ClassName: CompanyServiceImpl
 * @Description: 企业实现层实现类
 */
@Service
@Slf4j
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date currentTime = new DateTime(System.currentTimeMillis());

    @Resource
    CompanyMapper mapper;

    @Resource
    AuditLogMapper logMapper;

    /**
     * 企业分页查询
     *
     * @param companyName
     * @param page
     * @param limit
     * @return
     */
    @Override
    public IPage<Company> getCompany(String companyName, Integer page, Integer limit) {
        Page<Company> p = new Page<>(page, limit); //page,limit
        QueryWrapper<Company> queryWrapper = new QueryWrapper<Company>();
        if (!StringUtils.isEmpty(companyName)) {
            queryWrapper.like("company_name", companyName);
        }

        queryWrapper.orderByDesc("create_time");
        return mapper.selectPage(p, queryWrapper);
    }

    /**
     * 查看单个企业认证详情
     *
     * @param companyId
     * @return
     */
    @Override
    public Result<Company> getCompanyById(Long companyId) {
        Company data = mapper.selectById(companyId);
        return Result.success("查询成功",data);
    }

    /**
     * 根据企业Id获取审核记录
     *
     * @param companyId
     * @return
     */
    @Override
    public Result<List<AuditLog>> getAuditLogByCompanyId(Long companyId) {
        QueryWrapper<AuditLog> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("company_id", companyId);
        objectQueryWrapper.orderByDesc("audit_time", "application_time");
        objectQueryWrapper.last("limit 20");
        List<AuditLog> data = logMapper.selectList(objectQueryWrapper);
        return Result.success("查询成功",data);
    }

    /**
     * 企业入驻
     *
     * @param company
     * @return
     */
    @Override
    public Result<Company> insertCompany(Company company) {
        if (Objects.isNull(company)) {
            log.info("Company company error:{}", JSONObject.toJSONString(company));
            return Result.failed("企业信息不能为空");
        }

        try {
            company.setApplicationTime(formatter.format(currentTime));
            company.setCreateTime(formatter.format(currentTime));
            company.setIsDel(0);
            company.setAuditStatus(2);
            company.setCompanyCode(getFirstSpell(company.getCompanyName()));
            log.info("Company company :{}", JSONObject.toJSONString(company));

            //判断企业名称和企业统一社会信用代码如果存在,则不许添加
            QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("company_name", company.getCompanyName())
                    .or()
                    .eq("company_taxpayer", company.getCompanyTaxpayer());
            List<Company> Company = mapper.selectList(queryWrapper);

            if (!CollectionUtils.isEmpty(Company)) {
                log.info("Company queryWrapper error:{}", JSONObject.toJSONString(queryWrapper));
                return Result.failed("企业名称或者企业统一社会信用代码不能重复");
            }

            mapper.insert(company);

            //添加审核记录
            HashMap<Object, Object> map = new HashMap<>();
            map.put("company_name", company.getCompanyName());
            map.put("company_code", company.getCompanyCode());
            map.put("company_taxpayer", company.getCompanyTaxpayer());
            map.put("company_license", company.getCompanyLicense());
            map.put("company_contacts", company.getCompanyContacts());
            map.put("company_phone", company.getCompanyPhone());
            map.put("company_email", company.getCompanyEmail());
            map.put("company_address", company.getCompanyAddress());

            log.info("Company HashMap map:{}", JSONObject.toJSONString(map));

            AuditLog auditLog =
                    BeanUtil.toBean(company, AuditLog.class);
            auditLog.setAuditCompany(JSONObject.toJSONString(map));
            auditLog.setCompanyId(company.getCompanyId());

            log.info("Company insert auditLog :{}", JSONObject.toJSONString(auditLog));
            //新增一条日志
            logMapper.insert(auditLog);

            return Result.success("保存成功",company);
        } catch (Exception e) {
            log.error("Company insert Exception error:{}", JSONObject.toJSONString(e));
            e.printStackTrace();
            return Result.failed("保存失败");
        }
    }

    /**
     * 根据ID修改企业信息
     *
     * @param company
     * @return
     */
    @Override
    public Result<Company> updateCompany(Company company) {
        company.setUpdateTime(formatter.format(currentTime));
        mapper.updateById(company);
        return Result.success("修改成功", company);
    }

    /**
     * 企业信息审核
     *
     * @param company
     * @return
     */
    @Override
    public Result<Company> auditPass(Company company) {
        try {
//            VehicleEnterprise data = new VehicleEnterprise();
            company.setAuditTime(formatter.format(currentTime));
            log.info("VehicleEnterprise data :{}", JSONObject.toJSONString(company));
            mapper.updateById(company);

            Company byId = mapper.selectById(company.getCompanyId());

            AuditLog auditLog = new AuditLog();
            if (company.getAuditStatus().equals(0)) {
                auditLog.setRemark("企业审核通过");
                log.info("Company auditLog status = 0 :{}", JSONObject.toJSONString(auditLog));
                logMapper.insert(auditLog);
            } else if (company.getAuditStatus().equals(1)) {
                auditLog.setRemark("企业【" + byId.getCompanyName() + "】审核失败，失败原因：" + byId.getRemark());
                log.info("Company auditLog status = 1 :{}", JSONObject.toJSONString(auditLog));
                logMapper.insert(auditLog);
            }
//            AuditLog auditLog =
//                    BeanUtil.toBean(company, AuditLog.class);
            //修改日志
            auditLog.setCompanyId(company.getCompanyId());
            log.info("Company auditPass auditLog :{}", JSONObject.toJSONString(company));
            logMapper.insert(auditLog);

            return Result.success("保存成功",company);
        } catch (Exception e) {
            log.error("Company auditPass Exception :{}", JSONObject.toJSONString(e));
            return Result.failed("保存失败");
        }
    }

    /**
     * 根据ID删除企业
     *
     * @param companyId
     * @return
     */
    @Override
    public Result<Company> deleteCompany(Integer companyId) {
        mapper.deleteById(companyId);
        return Result.success("删除成功", companyId);
    }

    /**
     * 获取企业名称前五个汉子拼音首字母
     * @param companyName
     * @return
     */
    public static String getFirstSpell(String companyName) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = companyName.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < 3; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }
}
