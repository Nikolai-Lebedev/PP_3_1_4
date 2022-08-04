package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")

public class AdminController {
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
    }

    @GetMapping()
    public String allUsers(Model model){
        model.addAttribute("usersList", userService.getAll());
        model.addAttribute("rolseList", roleService.getRoles());

        return "users";
    }
    @GetMapping("/{id}")
    public String show (@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user";
    }
    @GetMapping("/new")
    public String newPerson ( Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "new";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute User user, Model model) {
        model.addAttribute("user", userService.add(user));
        return "redirect:/admin";
    }
    @GetMapping(value = "/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "edit";
    }
    @PostMapping(value = "/edit")
    public String update(@RequestParam() long id, User user){
        userService.updateUser(id,user);
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
