package com.lishuai.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lishuai.common.lang.Result;
import com.lishuai.entity.Complaint;
import com.lishuai.entity.Notice;
import com.lishuai.entity.User;
import com.lishuai.entity.dto.RegisterDTO;
import com.lishuai.entity.dto.UserDTO;
import com.lishuai.entity.vo.ComplaintVO;
import com.lishuai.entity.vo.DeliveryVO;
import com.lishuai.entity.vo.UserVO;
import com.lishuai.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lishuai
 * @since 2021-04-14
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    ComplaintService complaintService;

    @Autowired
    GarbageStationService garbageStationService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    OssService ossService;

    @Autowired
    GarbageService garbageService;

    /**
     * 管理员修改用户信息，不能用于修改管理员信息
     * @param user
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/editUser")
    public Result editUser(@RequestBody @Validated UserDTO user) throws Exception {
        userService.adminEditUser(user);
        return Result.success();
        //todo 管理员可以修改用户信息，包括用户名，但不能修改密码
    }

    /**
     * 管理员查询所有非管理员用户
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @GetMapping("/getAllUser")
    public Result getAllUser(@RequestParam(defaultValue = "1") Integer currentPage, String keywords){
        //分页插件，按时间排序
        Page page = new Page(currentPage, 5);
        IPage pageData;
        if (keywords!=null&& !keywords.equals("")){
            pageData = userService.page(page, new QueryWrapper<User>()
                    .like("username",keywords)
                    .ne("role_id",2)
                    .orderByDesc("create_time"));
        }else{
            pageData = userService.page(page, new QueryWrapper<User>()
                    .ne("role_id",2)
                    .orderByDesc("create_time"));
        }
        List<User> records = pageData.getRecords();
        List<UserVO> list = new ArrayList<>();
        records.forEach(v->{
            UserVO userVO = new UserVO();
            userVO.setStatus(v.getStatus()!=0);
            BeanUtil.copyProperties(v, userVO, "status");
            list.add(userVO);
        });
        pageData.setRecords(list);
        log.info("管理员查询所有用户成功！");
        return Result.success(pageData);

        //todo mybatis-plus 与 PageHelper存在依赖中的 mybatis冲突,导入 PageHelper需要屏蔽 mybatis
    }

    /**
     * 管理员删除用户
     * @param userId
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/deleteUser/{userId}")
    public Result deleteUser(@PathVariable int userId){
        adminService.deleteUser(userId);
        log.info("管理员删除用户成功！");
        return Result.success();
        //todo 删除用户的同时，也要删除用户的投递和投诉
    }

    /**
     * 管理员新增用户
     * @param registerDto
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody @Validated RegisterDTO registerDto) throws Exception {
        adminService.register(registerDto);
        log.info("管理员新增用户成功！");
        return Result.success();
    }

    /**
     * 管理员解禁用户
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/changUserStatus/{userId}/{status}")
    public Result changUserStatus(@PathVariable int userId, @PathVariable Boolean status)  {

        userService.changUserStatus(userId,status);
        log.info("管理员解禁用户成功！");
        return Result.success();
    }

    /**
     * 管理员查询所有投递记录
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @GetMapping("/getAllDelivery")
    public Result getAllDelivery(){
        List<DeliveryVO> allDelivery = deliveryService.getAllDelivery();
        log.info("管理员查询所有投递成功！");
        return Result.success(allDelivery);
    }

    /**
     * 管理员删除投递记录
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/deleteDelivery/{deliveryId}")
    public Result deleteDelivery(@PathVariable int deliveryId){
        deliveryService.removeById(deliveryId);
        log.info("管理员删除投递记录成功！");
        return Result.success();
    }


    /**
     * 管理员查询所有未回复投诉记录
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @GetMapping("/getAllNoAnswerComplaint")
    public Result getAllNoAnswerComplaint(){
        List<ComplaintVO> allComplaint = complaintService.getAllComplaint(0);
        log.info("管理员查询所有投诉成功！");
        return Result.success(allComplaint);
    }

    /**
     * 管理员查询所有已回复投诉记录
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @GetMapping("/getAllAnswerComplaint")
    public Result getAllAnswerComplaint(){
        List<ComplaintVO> allComplaint = complaintService.getAllComplaint(1);
        log.info("管理员查询所有投诉成功！");
        return Result.success(allComplaint);
    }

    /**
     * 管理员回复投诉
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/answerComplaint")
    public Result answerComplaint(@RequestParam("complaintId")int complaintId,
                                  @RequestParam("answer")String answer){
        Complaint complaint = complaintService.getById(complaintId);
        complaint.setAnswer(answer);
        complaint.setFlag(1);
        complaintService.saveOrUpdate(complaint);
        return Result.success();
    }

    /**
     * 管理员删除投递记录
     * @param complaintId
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/deleteComplaint/{complaintId}")
    public Result deleteComplaint(@PathVariable int complaintId){
        complaintService.removeById(complaintId);
        log.info("管理员删除投诉记录成功！");
        return Result.success();
    }

    /**
     * 管理员编辑垃圾站容量
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/changeCapacity")
    public Result changeCapacity(@RequestParam("stationId")int stationId,
                                 @RequestParam("categoryId")int categoryId,
                                 @RequestParam("capacityNumber")int capacityNumber){
        garbageStationService.changeCapacity(stationId,categoryId,capacityNumber);
        log.info("管理员修改垃圾站容量成功！");
        return Result.success();
    }

    /**
     * 管理员编辑公告
     * @return
     */
    @RequiresRoles("admin")
    @RequiresAuthentication
    @PostMapping("/changeNotice")
    public Result changeNotice(@RequestParam("content")String content){
        Notice notice = noticeService.getById(1);
        notice.setContent(content);
        noticeService.saveOrUpdate(notice);
        log.info("管理员修改公告成功！");
        return Result.success();
    }

    /**
     * 管理员将垃圾信息存入 ELS
     * @return
     */
    @GetMapping("/add")
    public Result add() throws IOException {

        Boolean aBoolean = adminService.addGarbageToEls();
        if(aBoolean){
            return Result.success("存入数据成功！");
        }else{
            return Result.success("存入数据失败！");
        }
    }
}
