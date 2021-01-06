package com.irm.web.admin;
/**
 * @author M.hj
 * @version 1.0
 * @date 2020/9/25 16:02
 */
import com.irm.po.User;
import com.irm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    UserService userService;
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes){
        User user = userService.checkUser(username, password);
        if(user!=null){
            user.setPassword(null);
            httpSession.setAttribute("user",user);
            return "admin/index";
        }else {
            redirectAttributes.addFlashAttribute("message","用户名和密码错误");
            //这种方式是拿不到message的，因为我们使用的是重定向
            //model.addAttribute("message","用户名和密码错误");
            return "redirect:/admin";
        }
    }
    // 这个方法是注销登录
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //使用removeAttribute这个方法销毁了user对象
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
