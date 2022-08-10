package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role getRoleByID(Long id) {
        return roleRepository.findRoleById(id);
    }


    public Set<Role> getRoles() {
        return new HashSet<>(roleRepository.findAll());
    }
    public Role getRoleByName(String name){
        return roleRepository.findRoleByName(name);
    }
    public HashSet<Role> getSetOfRoles(String[] rolesNames) {
        HashSet<Role> roleSet = new HashSet<>();
        for (String role : rolesNames) {
            roleSet.add(getRoleByName(role));
        }
        return roleSet;
    }

}
