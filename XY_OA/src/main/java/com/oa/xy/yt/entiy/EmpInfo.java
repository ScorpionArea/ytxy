package com.oa.xy.yt.entiy;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "emp_info", catalog = "")
public class EmpInfo implements Serializable {
  private static final long serialVersionUID = 3577340030441508293L;
  private String empId;
  private String empCode;
  private String empName;
  private String empCard;
  private String empBank;
  private String bankLoc;
  private String empCall;
  private String empAddr;
  private Date empHiredate;
  private String empRemark;
  private String state;
  private String creator;
  private Timestamp createdTim;
  private String changer;
  private Timestamp changedTim;

  private List<JobType> jobTypes;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid")
  @Column(name = "EMP_ID")
  public String getEmpId() {
    return empId;
  }

  public void setEmpId(String empId) {
    this.empId = empId;
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
  @Column(name = "EMP_NAME")
  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName;
  }

  @Basic
  @Column(name = "EMP_CARD")
  public String getEmpCard() {
    return empCard;
  }

  public void setEmpCard(String empCard) {
    this.empCard = empCard;
  }

  @Basic
  @Column(name = "EMP_BANK")
  public String getEmpBank() {
    return empBank;
  }

  public void setEmpBank(String empBank) {
    this.empBank = empBank;
  }

  @Basic
  @Column(name = "BANK_LOC")
  public String getBankLoc() {
    return bankLoc;
  }

  public void setBankLoc(String bankLoc) {
    this.bankLoc = bankLoc;
  }

  @Basic
  @Column(name = "EMP_CALL")
  public String getEmpCall() {
    return empCall;
  }

  public void setEmpCall(String empCall) {
    this.empCall = empCall;
  }

  @Basic
  @Column(name = "EMP_ADDR")
  public String getEmpAddr() {
    return empAddr;
  }

  public void setEmpAddr(String empAddr) {
    this.empAddr = empAddr;
  }

  @Basic
  @Column(name = "EMP_HIREDATE")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
  public Date getEmpHiredate() {
    return empHiredate;
  }

  public void setEmpHiredate(Date empHiredate) {
    this.empHiredate = empHiredate;
  }

  @Basic
  @Column(name = "EMP_REMARK")
  public String getEmpRemark() {
    return empRemark;
  }

  public void setEmpRemark(String empRemark) {
    this.empRemark = empRemark;
  }

  @Basic
  @Column(name = "STATE")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
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
      name = "REF_EMPINFO_JOBTYPE",
      joinColumns = {@JoinColumn(name = "REF_EMP_ID", referencedColumnName = "EMP_ID")},
      inverseJoinColumns = {@JoinColumn(name = "REF_JOB_ID", referencedColumnName = "JOB_ID")})
  public List<JobType> getJobTypes() {
    return jobTypes;
  }

  public void setJobTypes(List<JobType> jobTypes) {
    this.jobTypes = jobTypes;
  }
}
