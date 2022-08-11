package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

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
    public String index(Principal principal, Model model) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute User user, Model model) {
        model.addAttribute("newUser", userService.add(user));
        return "redirect:/admin";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}


