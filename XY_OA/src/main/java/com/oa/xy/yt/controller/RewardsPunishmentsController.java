package com.oa.xy.yt.controller;

import com.oa.xy.base.entiy.SysUser;
import com.oa.xy.pojo.EzuiGridData;
import com.oa.xy.yt.entiy.RewardsPunishments;
import com.oa.xy.yt.service.RewardsPunishmentsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("RewardsPunishmentsControl")
public class RewardsPunishmentsController {
  Sort.Direction DIRECTION = Sort.DEFAULT_DIRECTION;
  @Autowired RewardsPunishmentsService rewardsPunishmentsService;

  @RequestMapping(value = "/rewardsPunishments.htm")
  public String rewardsPunishments(Model model) {
    return "yt/rewardsPunishments";
  }

  @RequiresPermissions("rP:find")
  @RequestMapping(value = "/find", method = RequestMethod.POST)
  @ResponseBody
  public EzuiGridData find(Integer page, Integer rows, String sort, String order) {
    EzuiGridData ezuiGridData = new EzuiGridData();
    if (("desc").equals(order)) {
      DIRECTION = Sort.Direction.DESC;
    } else {
      DIRECTION = Sort.Direction.ASC;
    }
    Specification specification =
        new Specification<RewardsPunishments>() {
          @Override
          public Predicate toPredicate(
              Root<RewardsPunishments> root,
              CriteriaQuery<?> criteriaQuery,
              CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            //            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //            if (!StringUtils.isEmpty(userState)) {
            //              predicates.add(criteriaBuilder.equal(root.get("userState"), userState));
            //            }
            //            if (!StringUtils.isEmpty(endDate)) {
            //              try {
            //                predicates.add(
            //                    criteriaBuilder.lessThanOrEqualTo(
            //                        root.get("tideDate").as(Date.class), sdf.parse(endDate)));
            //              } catch (ParseException e) {
            //                e.printStackTrace();
            //              }
            //            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
          }
        };
    if (StringUtils.isEmpty(page) && StringUtils.isEmpty(rows)) {
      List<RewardsPunishments> rewardsPunishmentss =
          rewardsPunishmentsService.findAll(specification, new Sort(DIRECTION, "rpDate"));
      ezuiGridData.setRows(rewardsPunishmentss);
    } else {
      Pageable pageable = new PageRequest(page - 1, rows, DIRECTION, sort);
      Page<RewardsPunishments> rewardsPunishmentss =
          rewardsPunishmentsService.findAll(specification, pageable);
      ezuiGridData.setRows(rewardsPunishmentss.getContent());
      ezuiGridData.setTotal(rewardsPunishmentss.getTotalElements());
    }
    return ezuiGridData;
  }

  @RequiresPermissions("rP:save")
  @RequestMapping(value = "/save", method = RequestMethod.POST)
  @ResponseBody
  public RewardsPunishments save(RewardsPunishments rewardsPunishments) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    rewardsPunishments.setCreator(sUser.getUserCode());
    rewardsPunishments.setCreatedTim(new Timestamp(date.getTime()));
    rewardsPunishments.setChanger(sUser.getUserCode());
    rewardsPunishments.setChangedTim(new Timestamp(date.getTime()));
    return rewardsPunishmentsService.save(rewardsPunishments);
  }

  @RequiresPermissions("rP:update")
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  @ResponseBody
  public RewardsPunishments update(RewardsPunishments rewardsPunishments) {
    SysUser sUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
    Date date = new Date();
    rewardsPunishments.setChanger(sUser.getUserCode());
    rewardsPunishments.setChangedTim(new Timestamp(date.getTime()));
    return rewardsPunishmentsService.save(rewardsPunishments);
  }

  @RequiresPermissions("rP:delete")
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  @ResponseBody
  public void delete(String id) {
    rewardsPunishmentsService.deleteById(id);
  }
}
