package com.li.lrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.li.lrms.mapper.LoginMapper;
import com.li.lrms.model.loginrole.logins;
import com.li.lrms.model.user.User;
import com.li.lrms.service.LoginService;
import com.li.lrms.util.JwtUtils;
import com.li.lrms.util.LrmsException;
import com.li.lrms.util.MD5;
import com.li.lrms.vo.login.EditVo;
import com.li.lrms.vo.login.LoginVo;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;


@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {

    @Override
    public String login(LoginVo loginVo) throws SQLException, IOException, InterruptedException {   //  注意：  获取的用户名和表中的id比较  username==user-id
        String password = MD5.encrypt(loginVo.getPassword());
        String userId = loginVo.getUsername();
        String role = loginVo.getRole();

        logins login=new logins();
        if (userId==null||password==null)                  //少输入东西
            throw new LrmsException(201,"请输入用户名或密码");

        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_id",userId);      //获取该对象
        wrapper.eq("role",role);
        User user = baseMapper.selectOne(wrapper);
        if ((!password.equals(user.getPassword()))){
            return null;
        }
        if (user.getStatus()==1)
            return null;
        String jwtToken = JwtUtils.getJwtToken(user.getUserId(), user.getUserName());
        return jwtToken;

    }

    //获取用户id，返回用户实体
    @Override
    public User getByUserId(String memberId) {

        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",memberId);
        User user = baseMapper.selectOne(queryWrapper);
        return user;


    }

    @Override
    public void updateInfo(EditVo editVo ,User user) {
        String phone = editVo.getPhone();
        String emali = editVo.getEmail();
        String pass = editVo.getPass();
        String checkPass = editVo.getCheckPass();

        if (pass.equals(checkPass)){
            user.setPassword(MD5.encrypt(pass));
        }

        user.setPhone(phone);
        user.setEmail(emali);

        baseMapper.updateById(user);



    }
}
