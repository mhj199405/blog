package com.irm.service;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
