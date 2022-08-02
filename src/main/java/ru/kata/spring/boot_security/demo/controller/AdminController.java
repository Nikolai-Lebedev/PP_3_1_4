package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")

public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model){
        model.addAttribute("usersList", userService.getAll());
        return "users";
    }
    @GetMapping("/{id}")
    public String show (@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "user";
    }
    @GetMapping("/new")
    public String newPerson (Model model){
        model.addAttribute("user", new User());
        return "new";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, Model model){
        model.addAttribute("user", userService.add(user));
        return "redirect:/admin";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
