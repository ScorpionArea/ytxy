package com.oa.xy.yt.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "pay_record", schema = "xy_oa", catalog = "")
public class PayRecord implements Serializable {
  private String payId;
  private Date payDate;
  private String empCode;
  private String jobCode;
  private Float jobBasepay;
  private Float jobPay;
  private Float jobTraffic;
  private Float jobFood;
  private Float jobOther;
  private Float planPref;
  private Float realPref;
  private Float rate;
  private Float perfCount;
  private Float rpCount;
  private Float allCount;
  private String creator;
  private Timestamp createdTim;
  private String changer;
  private Timestamp changedTim;

  private EmpInfo empInfo;
  private String empName;
  private JobType jobType;
  private String jobName;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid")
  @Column(name = "PAY_ID")
  public String getPayId() {
    return payId;
  }

  public void setPayId(String payId) {
    this.payId = payId;
  }

  @Basic
  @Column(name = "PAY_DATE")
  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  @Basic
  @Column(name = "EMP_CODE")
  public String getEmpCode() {
    return empCode;
  }

  public void setEmpCode(String empCode) {
    this.empCode = empCode;
  }

  @Basic
  @Column(name = "JOB_CODE")
  public String getJobCode() {
    return jobCode;
  }

  public void setJobCode(String jobCode) {
    this.jobCode = jobCode;
  }

  @Basic
  @Column(name = "JOB_BASEPAY")
  public Float getJobBasepay() {
    return jobBasepay;
  }

  public void setJobBasepay(Float jobBasepay) {
    this.jobBasepay = jobBasepay;
  }

  @Basic
  @Column(name = "JOB_PAY")
  public Float getJobPay() {
    return jobPay;
  }

  public void setJobPay(Float jobPay) {
    this.jobPay = jobPay;
  }

  @Basic
  @Column(name = "JOB_TRAFFIC")
  public Float getJobTraffic() {
    return jobTraffic;
  }

  public void setJobTraffic(Float jobTraffic) {
    this.jobTraffic = jobTraffic;
  }

  @Basic
  @Column(name = "JOB_FOOD")
  public Float getJobFood() {
    return jobFood;
  }

  public void setJobFood(Float jobFood) {
    this.jobFood = jobFood;
  }

  @Basic
  @Column(name = "JOB_OTHER")
  public Float getJobOther() {
    return jobOther;
  }

  public void setJobOther(Float jobOther) {
    this.jobOther = jobOther;
  }

  @Basic
  @Column(name = "PLAN_PREF")
  public Float getPlanPref() {
    return planPref;
  }

  public void setPlanPref(Float planPref) {
    this.planPref = planPref;
  }

  @Basic
  @Column(name = "REAL_PREF")
  public Float getRealPref() {
    return realPref;
  }

  public void setRealPref(Float realPref) {
    this.realPref = realPref;
  }

  @Basic
  @Column(name = "RATE")
  public Float getRate() {
    return rate;
  }

  public void setRate(Float rate) {
    this.rate = rate;
  }

  @Basic
  @Column(name = "PERF_COUNT")
  public Float getPerfCount() {
    return perfCount;
  }

  public void setPerfCount(Float perfCount) {
    this.perfCount = perfCount;
  }

  @Basic
  @Column(name = "RP_COUNT")
  public Float getRpCount() {
    return rpCount;
  }

  public void setRpCount(Float rpCount) {
    this.rpCount = rpCount;
  }

  @Basic
  @Column(name = "ALL_COUNT")
  public Float getAllCount() {
    return allCount;
  }

  public void setAllCount(Float allCount) {
    this.allCount = allCount;
  }

  @Basic
  @Column(name = "CREATOR")
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  @Basic
  @Column(name = "CREATED_TIM")
  public Timestamp getCreatedTim() {
    return createdTim;
  }

  public void setCreatedTim(Timestamp createdTim) {
    this.createdTim = createdTim;
  }

  @Basic
  @Column(name = "CHANGER")
  public String getChanger() {
    return changer;
  }

  public void setChanger(String changer) {
    this.changer = changer;
  }

  @Basic
  @Column(name = "CHANGED_TIM")
  public Timestamp getChangedTim() {
    return changedTim;
  }

  public void setChangedTim(Timestamp changedTim) {
    this.changedTim = changedTim;
  }

  @JoinColumn(
      name = "EMP_CODE",
      referencedColumnName = "EMP_CODE",
      updatable = false,
      insertable = false)
  @OneToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  public EmpInfo getEmpInfo() {
    return empInfo;
  }

  public void setEmpInfo(EmpInfo empInfo) {
    this.empInfo = empInfo;
  }

  @Transient
  public String getEmpName() {
    if (empInfo != null) {
      return empInfo.getEmpName();
    }
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  @JoinColumn(
      name = "JOB_CODE",
      referencedColumnName = "JOB_CODE",
      updatable = false,
      insertable = false)
  @OneToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  public JobType getJobType() {
    return jobType;
  }

  public void setJobType(JobType jobType) {
    this.jobType = jobType;
  }

  @Transient
  public String getJobName() {
    if (jobType != null) {
      return jobType.getJobName();
    }
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }
}
