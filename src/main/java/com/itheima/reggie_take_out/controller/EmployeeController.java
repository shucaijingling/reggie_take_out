package com.itheima.reggie_take_out.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie_take_out.common.R;
import com.itheima.reggie_take_out.entity.Employee;
import com.itheima.reggie_take_out.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("login")
    public R<Employee> login(HttpServletRequest request,
                             @RequestBody Employee employee){
        //密码加密进行比对
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(queryWrapper);

        //查询数据库为空，返回登陆失败
        if (one == null){
            return R.error("登录失败");
        }
        //传入的密码跟数据库的密码比对
        //注意：数据库对用户名加了唯一约束，用户名不能重复
        if (!password.equals(one.getPassword())){
            return R.error("密码错误");
        }
        //判断账号是否被禁用
        if (one.getStatus() == 0){
            return R.error("账号已被禁用");
        }
        //登录成功，把用户id存入session
        request.getSession().setAttribute("employee", one.getId());
        return R.success(one);
    }

    @PostMapping("logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增用户
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,
                          @RequestBody Employee employee){
        log.info("新增员工信息：{}",employee.toString());

        //给用户添加默认密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //添加创建时间
//        employee.setCreateTime(LocalDateTime.now());
        //添加修改时间
//        employee.setUpdateTime(LocalDateTime.now());
        //获取当前用户id
//        Long empId = (Long) request.getSession().getAttribute("employee");
        //添加创建人的用户id
//        employee.setCreateUser(empId);
        //添加修改人的用户id
//        employee.setUpdateUser(empId);
        //保存用户
        employeeService.save(employee);
        return R.success("创建用户成功");
    }


    //请求网址: http://localhost:8080/employee/page?page=1&pageSize=10
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //进行分页查询
        employeeService.page(pageInfo,queryWrapper);

        //返回
        return R.success(pageInfo);
    }

    /**
     * 前端js存在Long型丢失精度，转换成String
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     *  根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee!=null){
            return R.success(employee);
        }

        return R.error("查询失败");
    }
}
