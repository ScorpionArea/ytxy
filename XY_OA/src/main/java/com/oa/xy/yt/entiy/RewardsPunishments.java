package com.oa.xy.yt.entiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "rewards_punishments", schema = "xy_oa", catalog = "")
public class RewardsPunishments implements Serializable {
  private static final long serialVersionUID = -8357496640114589018L;
  private String rpId;
  private String rpDate;
  private String empCode;
  private String rpType;
  private BigDecimal rpNum;
  private String rpRemark;
  private String creator;
  private Timestamp createdTim;
  private String changer;
  private Timestamp changedTim;

  private EmpInfo empInfo;
  private String empName;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid")
  @Column(name = "RP_ID")
  public String getRpId() {
    return rpId;
  }

  public void setRpId(String rpId) {
    this.rpId = rpId;
  }

  @Basic
  @Column(name = "RP_DATE")
  public String getRpDate() {
    return rpDate;
  }

  public void setRpDate(String rpDate) {
    this.rpDate = rpDate;
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
  @Column(name = "RP_TYPE")
  public String getRpType() {
    return rpType;
  }

  public void setRpType(String rpType) {
    this.rpType = rpType;
  }

  @Basic
  @Column(name = "RP_NUM")
  public BigDecimal getRpNum() {
    return rpNum;
  }

  public void setRpNum(BigDecimal rpNum) {
    this.rpNum = rpNum;
  }

  @Basic
  @Column(name = "RP_REMARK")
  public String getRpRemark() {
    return rpRemark;
  }

  public void setRpRemark(String rpRemark) {
    this.rpRemark = rpRemark;
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
}
