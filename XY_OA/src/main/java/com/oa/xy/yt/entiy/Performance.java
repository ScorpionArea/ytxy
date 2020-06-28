package com.oa.xy.yt.entiy;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Performance implements Serializable {
  private static final long serialVersionUID = -763706311829305040L;
  private String perfId;
  private String perfCode;
  private String perfName;
  private Float per05;
  private Float job05;
  private Float per58;
  private Float job58;
  private Float per810;
  private Float job810;
  private Float per10;
  private Float job10;
  private String creator;
  private Timestamp createdTim;
  private String changer;
  private Timestamp changedTim;

  private boolean ck;

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "uuid")
  @Column(name = "PERF_ID")
  public String getPerfId() {
    return perfId;
  }

  public void setPerfId(String perfId) {
    this.perfId = perfId;
  }

  @Basic
  @Column(name = "PERF_CODE")
  public String getPerfCode() {
    return perfCode;
  }

  public void setPerfCode(String perfCode) {
    this.perfCode = perfCode;
  }

  @Basic
  @Column(name = "PERF_NAME")
  public String getPerfName() {
    return perfName;
  }

  public void setPerfName(String perfName) {
    this.perfName = perfName;
  }

  @Basic
  @Column(name = "PER_05")
  public Float getPer05() {
    return per05;
  }

  public void setPer05(Float per05) {
    this.per05 = per05;
  }

  @Basic
  @Column(name = "JOB_05")
  public Float getJob05() {
    return job05;
  }

  public void setJob05(Float job05) {
    this.job05 = job05;
  }

  @Basic
  @Column(name = "PER_58")
  public Float getPer58() {
    return per58;
  }

  public void setPer58(Float per58) {
    this.per58 = per58;
  }

  @Basic
  @Column(name = "JOB_58")
  public Float getJob58() {
    return job58;
  }

  public void setJob58(Float job58) {
    this.job58 = job58;
  }

  @Basic
  @Column(name = "PER_810")
  public Float getPer810() {
    return per810;
  }

  public void setPer810(Float per810) {
    this.per810 = per810;
  }

  @Basic
  @Column(name = "JOB_810")
  public Float getJob810() {
    return job810;
  }

  public void setJob810(Float job810) {
    this.job810 = job810;
  }

  @Basic
  @Column(name = "PER_10")
  public Float getPer10() {
    return per10;
  }

  public void setPer10(Float per10) {
    this.per10 = per10;
  }

  @Basic
  @Column(name = "JOB_10")
  public Float getJob10() {
    return job10;
  }

  public void setJob10(Float job10) {
    this.job10 = job10;
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

  @Transient
  public boolean isCk() {
    return ck;
  }

  public void setCk(boolean ck) {
    this.ck = ck;
  }
}
