package com.oa.xy.yt.controller;

import com.oa.xy.base.entiy.SysUser;
import com.oa.xy.yt.entiy.EmpInfo;
import com.oa.xy.yt.entiy.JobType;
import com.oa.xy.yt.entiy.PayRecord;
import com.oa.xy.yt.entiy.Performance;
import com.oa.xy.yt.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("PayControl")
public class PayController {
  @Autowired PayRecordService payRecordService;
  @Autowired EmpInfoService empInfoService;
  @Autowired JobTypeService jobTypeService;
  @Autowired PerformanceService performanceService;
  @Autowired RewardsPunishmentsService rewardsPunishmentsService;
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;

  @RequestMapping(value = "/pay.htm")
  public String pay(Model model) {
    return "yt/pay";
  }

  @RequiresPermissions("pay:pay")
  @RequestMapping(value = "/pay")
  @ResponseBody
  public PayRecord pay(
      String empCode, String jobCode, String month, Float planPerf, Float realPerf, Float rate)
      throws ParseException {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM"); // 注意月份是MM
    Date payDate = simpleDateFormat.parse(month);
    PayRecord payRecord =
        payRecordService.findByEmpCodeAndPayDate(empCode, new java.sql.Date(payDate.getTime()));
    if (payRecord == null) {
      payRecord = new PayRecord();
      EmpInfo empInfo = empInfoService.findByEmpCode(empCode);
      payRecord.setPayDate(new java.sql.Date(payDate.getTime()));
      payRecord.setEmpCode(empCode);
      payRecord.setJobCode(jobCode);
      payRecord.setPlanPref(planPerf);
      payRecord.setRealPref(realPerf);
      payRecord.setRate(rate);
      JobType jobType = null;
      Performance perf = null;
      if (empInfo.getEmpHiredate().getYear() == payDate.getYear()
          && empInfo.getEmpHiredate().getMonth() == payDate.getMonth()) {

      } else {
        if ((jobCode).equals(empInfo.getJobTypes().get(0).getJobCode())) {
          jobType = empInfo.getJobTypes().get(0);
          perf = jobType.getPerformances().get(0);
        } else {
          jobType = jobTypeService.findByJobCode(jobCode);
          perf = jobType.getPerformances().get(0);
        }
        payRecord.setJobBasepay(jobType.getJobBasepay());
        if (rate <= 0.5) {
          payRecord.setJobPay(jobType.getJobPay() * perf.getJob05() / 100);
          payRecord.setPerfCount(realPerf * perf.getPer05() / 100);
        } else if (rate <= 0.8) {
          payRecord.setJobPay(jobType.getJobPay() * perf.getJob58() / 100);
          payRecord.setPerfCount(realPerf * perf.getPer58() / 100);
        } else if (rate <= 1) {
          payRecord.setJobPay(jobType.getJobPay() * perf.getJob810() / 100);
          payRecord.setPerfCount(realPerf * perf.getPer810() / 100);
        } else {
          payRecord.setJobPay(jobType.getJobPay() * perf.getJob10() / 100);
          payRecord.setPerfCount(realPerf * perf.getPer10() / 100);
        }
      }
      payRecord.setJobTraffic(jobType.getJobTraffic());
      payRecord.setJobFood(jobType.getJobFood());
      payRecord.setJobOther(jobType.getJobOther());
      Float rpCount = rewardsPunishmentsService.sumByRpDateAndEmpCode(payDate, empCode);
      payRecord.setRpCount(rpCount);
      payRecord.setAllCount(
          payRecord.getJobBasepay()
              + payRecord.getJobPay()
              + payRecord.getJobTraffic()
              + payRecord.getJobFood()
              + (payRecord.getJobOther() == null ? 0 : payRecord.getJobOther())
              + payRecord.getPerfCount()
              + (payRecord.getRpCount() == null ? 0 : payRecord.getRpCount()));
      SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
      Date date = new Date();
      payRecord.setCreator(sUser.getUserCode());
      payRecord.setCreatedTim(new Timestamp(date.getTime()));
      payRecord.setChanger(sUser.getUserCode());
      payRecord.setChangedTim(new Timestamp(date.getTime()));
      return payRecordService.save(payRecord);
    } else {
      return payRecord;
    }
  }
}
