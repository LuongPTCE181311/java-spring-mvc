package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.domain.User;
import com.example.demo.service.UserSevice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UserController {

    private final UserSevice userSevice;

    public UserController(UserSevice userSevice) {
        this.userSevice = userSevice;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUsers = this.userSevice.getAllUserByEmail("hoidanit@gmail.com");
        System.out.println(arrUsers);
        model.addAttribute("content", "hello content form cotroller");
        return "hello";
    }

    @RequestMapping("/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping("/admin/user")
    public String getTableUser(Model model) {
        List<User> users = this.userSevice.getAllUser();
        model.addAttribute("users1", users);
        return "admin/user/table-users";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User UserInfor = this.userSevice.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", UserInfor);
        return "admin/user/show";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUser(Model model, @PathVariable long id) {
        User UserInfor = this.userSevice.getUserById(id);
        model.addAttribute("newUser", UserInfor);
        return "admin/user/update";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUser(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User hoidanit) {
        this.userSevice.deleteById(hoidanit.getId());
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User hoidanit) {
        User UserInfor = this.userSevice.getUserById(hoidanit.getId());
        if(UserInfor != null){
            UserInfor.setAddress(hoidanit.getAddress());
            UserInfor.setFullName(hoidanit.getFullName());
            UserInfor.setPhone(hoidanit.getPhone());
            this.userSevice.handleSaveUser(UserInfor);
        }
        return "redirect:/admin/user";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUserPage(Model model, @ModelAttribute("newUser") User hoidanit) {
        this.userSevice.handleSaveUser(hoidanit);
        return "redirect:/admin/user";
    }
}


