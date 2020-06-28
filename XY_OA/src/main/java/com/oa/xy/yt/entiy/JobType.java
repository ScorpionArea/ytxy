package com.oa.xy.yt.entiy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "job_type", catalog = "")
public class JobType implements Serializable {
  private static final long serialVersionUID = 7492207151750684408L;
  private String jobId;
  private String jobCode;
  private String jobName;
  private Float jobBasepay;
  private Float jobPay;
  private Float jobTraffic;
  private Float jobFood;
  private Float jobOther;
  private Float jobTotal;
  private Float jobOverpay;
  private String creator;
  private Timestamp createdTim;
  private String changer;
  private Timestamp changedTim;

  private List<Performance> performances;
  private boolean ck;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid")
  @Column(name = "JOB_ID")
  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
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
  @Column(name = "JOB_NAME")
  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
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
  @Column(name = "JOB_TOTAL")
  public Float getJobTotal() {
    return jobTotal;
  }

  public void setJobTotal(Float jobTotal) {
    this.jobTotal = jobTotal;
  }

  @Basic
  @Column(name = "JOB_OVERPAY")
  public Float getJobOverpay() {
    return jobOverpay;
  }

  public void setJobOverpay(Float jobOverpay) {
    this.jobOverpay = jobOverpay;
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

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(
      name = "REF_JOBTYPE_PERFORMANCE",
      joinColumns = {@JoinColumn(name = "REF_JOB_ID", referencedColumnName = "JOB_ID")},
      inverseJoinColumns = {@JoinColumn(name = "REF_PERF_ID", referencedColumnName = "PERF_ID")})
  public List<Performance> getPerformances() {
    return performances;
  }

  public void setPerformances(List<Performance> performances) {
    this.performances = performances;
  }

  @Transient
  public boolean isCk() {
    return ck;
  }

  public void setCk(boolean ck) {
    this.ck = ck;
  }
}
