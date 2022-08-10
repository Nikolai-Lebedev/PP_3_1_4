package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

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

//    @GetMapping()
//    public String allUsers(Principal principal,Model model){
//        model.addAttribute("usersList", userService.getAll());
//        model.addAttribute("rolseList", roleService.getRoles());
//        model.addAttribute("thisUser", userService.findByName(principal.getName()));
////        model.addAttribute("newUser", new User());
//
//        return "111";
//    }
//
//    @GetMapping("/new")
//    public String newPerson (Model model){
//        model.addAttribute("user", new User());
//        model.addAttribute("roles", roleService.getRoles());
//        return "newUser";
//    }
//
//
//    @PostMapping("/new")
//    public String save(@ModelAttribute User user) {
//        userService.add(user);
//        return "redirect:/admin";
//    }
//
//    @PostMapping(value = "/{id}/edit")
//    public String update(@RequestParam() Long id, User user){
//        userService.updateUser(id,user);
//        return "redirect:/admin";
//    }
//    @DeleteMapping (value = "/{id}/delete")
//    public String delete(@PathVariable("id") Long id){
//        userService.delete(id);
//        return "redirect:/admin";
//    }


    @GetMapping()
    public String index(Principal principal, Model model) {
        model.addAttribute("user", userService.findByName(principal.getName()));
        model.addAttribute("users", userService.getAll());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "all_users";
    }

//    @PostMapping("/new")
//    public String create(@ModelAttribute("user") User newUser,
//                         @RequestParam("roles") String[] roles) {
//        newUser.setRoles(roleService.getSetOfRoles(roles));
//        userService.add(newUser);
//        return "redirect:/admin";
//    }
    @PostMapping("/new")
    public String create(@ModelAttribute User user, Model model) {
        model.addAttribute("newUser", userService.add(user));
        return "redirect:/admin";
    }


    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user,
                         @RequestParam("roles") String[] roles,
                         @RequestParam() Long id) {
        user.setRoles(roleService.getSetOfRoles(roles));
        userService.updateUser(id,user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}


