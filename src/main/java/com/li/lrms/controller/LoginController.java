package com.li.lrms.controller;

import com.li.lrms.common.result.Result;
import com.li.lrms.model.logintoken.LoginToken;
import com.li.lrms.model.user.User;
import com.li.lrms.service.LoginService;
import com.li.lrms.util.JwtUtils;
import com.li.lrms.util.MD5;
import com.li.lrms.vo.login.EditVo;
import com.li.lrms.vo.login.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

@CrossOrigin
@RestController
@RequestMapping("/admin/member")
public class LoginController {
    @Autowired
    private LoginService loginService;
    //1.登录验证
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) throws SQLException, IOException, InterruptedException {      //返回token值，使用jwt生成
        String token=loginService.login(loginVo);
        LoginToken loginToken=new LoginToken();
        loginToken.setToken(token);
        return Result.ok(loginToken);
    }
    //2.根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) throws SQLException, IOException {   //获取token，解码用户信息
        String memberId= JwtUtils.getMemberIdByJwtToken(request);
        User member = loginService.getByUserId(memberId);
        return Result.ok(member);
    }
    //3.登出
    @PostMapping("logout")
    public Result logout(@RequestHeader(value = "X-Token") String token){

            String resStr="";
            return Result.ok(resStr);

    }
    //4.修改个人信息
    //1.电话号码 2.密码  3.邮箱  4.
    @PostMapping("editInfo")
    public Result editInfo(@RequestBody(required = false) EditVo editVo,HttpServletRequest request) throws SQLException, IOException {

        String userId= JwtUtils.getMemberIdByJwtToken(request);
        User user = loginService.getByUserId(userId);   //获得该用户信息

        loginService.updateInfo(editVo,user);
        return Result.ok();

    }
    //5. 注册
    @PostMapping("stuRegister")
    public Result stuRegister(@RequestBody User user){

        String pwd = MD5.encrypt(user.getPassword());
        user.setPassword(pwd);
        boolean save = loginService.save(user);
        return save?Result.ok():Result.fail();
    }

}
