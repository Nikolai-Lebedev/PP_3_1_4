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

    @GetMapping()
    public String allUsers(Principal principal,Model model){
        model.addAttribute("usersList", userService.getAll());
        model.addAttribute("rolseList", roleService.getRoles());
        model.addAttribute("thisUser", userService.findByName(principal.getName()));
//        model.addAttribute("newUser", new User());

        return "111";
    }
//    @GetMapping("/{id}")
//    public String show (@PathVariable("id") Long id, Model model){
//        model.addAttribute("user", userService.findById(id));
//        return "user";
//    }
    @GetMapping("/new")
    public String newPerson (Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());
        return "newUser";
    }

//    @PostMapping("/add")
//    public String save(@ModelAttribute("user") User user) {
//        userService.add(user);
//        return "redirect:/admin";
//    }
    @PostMapping("/new")
    public String save(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }
//    @GetMapping(value = "/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id){
//        model.addAttribute("user", userService.findById(id));
//        model.addAttribute("roles", roleService.getRoles());
//        return "newUser";
//    }
    @PostMapping(value = "/{id}/edit")
    public String update(@RequestParam() long id, User user){
        userService.updateUser(id,user);
        return "redirect:/admin";
    }
    @DeleteMapping (value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/admin";
    }

//    private void getUserRoles(User user) {
//        user.setRoles(user.getRoles().stream()
//                .map(role -> roleService.getRole(role.getUserRole()))
//                .collect(Collectors.toSet()));
//    }

}
