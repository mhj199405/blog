package com.irm.dao;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}
