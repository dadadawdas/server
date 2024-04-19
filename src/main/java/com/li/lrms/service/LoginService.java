package com.li.lrms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.lrms.model.user.User;
import com.li.lrms.vo.login.EditVo;
import com.li.lrms.vo.login.LoginVo;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginService extends IService<User>{

    String login(LoginVo loginVo) throws SQLException, IOException, InterruptedException;

    User getByUserId(String memberId);

    void updateInfo(EditVo editVo,User user);
}
