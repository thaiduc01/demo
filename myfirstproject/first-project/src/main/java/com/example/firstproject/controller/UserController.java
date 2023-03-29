package com.example.firstproject.controller;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.firstproject.entity.UserEntity;
import com.example.firstproject.service.UserService;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    
    private static final String REDIRECTING_PAGE = "redirect:/list";

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String listUser(Model model) {
        List<UserEntity> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        model.addAttribute("currentPage",1);
        model.addAttribute("totalPage", userService.getUserByPage(1).getTotalPages());
        model.addAttribute("totalItems", userService.getUserByPage(1).getTotalElements());
        model.addAttribute("listUser", userService.getUserByPage(1).getContent());
        model.addAttribute("path", "list");
        return "User";
    }
    
    @GetMapping("/list/{page}")
    public String listUsersPaging(Model model, @PathVariable(name="page") int page) {
        
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage",userService.getUserByPage(page).getTotalPages());
        model.addAttribute("totalItems", userService.getUserByPage(page).getTotalElements());
        model.addAttribute("listUser", userService.getUserByPage(page).getContent());
        model.addAttribute("path", "list");
        return "User";
    }
    
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("user",new UserEntity());
        return "AddUser";
    }
    
    @RequestMapping(value = "/doAddUser",method = RequestMethod.POST)
    public String doAddUser(@Valid @ModelAttribute(name = "user") UserEntity user,BindingResult result,Model model,HttpServletRequest request) {

        userService.save(user);
        List<UserEntity> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        return REDIRECTING_PAGE;
    }
    
    
    
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    private String showUpdateForm(@RequestParam("id") Long id, Model model) {
        Optional<UserEntity> otpUser = userService.findById(id);
        UserEntity user = otpUser.get();
        model.addAttribute("user", user);
        return "Update-user";
    }
    
    @RequestMapping(value = "/doUpdateUser",method = RequestMethod.POST)
    private String doUpdateUser( @ModelAttribute(name = "user") UserEntity user, BindingResult result ,Model model) {
        model.addAttribute("user", new UserEntity());
        user.setId(user.getId());
        userService.save(user);
        List<UserEntity> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        return REDIRECTING_PAGE;
    }
    
    @RequestMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id,Model model) {
        userService.deleteById(id);
        List<UserEntity> listUser = userService.getAllUser();
        model.addAttribute("listUser", listUser);
        return REDIRECTING_PAGE;
    }
    
    @RequestMapping("/doSearch")
    private String findByKeyWord(@RequestParam("keyword") String keyword, Model model) {
        List<UserEntity> listUser = userService.findUserByKey(keyword);
        model.addAttribute("listUser", listUser);
        return "User";
    }
}
