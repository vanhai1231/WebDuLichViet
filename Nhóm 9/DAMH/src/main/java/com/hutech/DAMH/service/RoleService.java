package com.hutech.DAMH.service;

import com.hutech.DAMH.model.Role;
import com.hutech.DAMH.repository.IRoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class RoleService {
    private final IRoleRepository roleRepository;
    public List<Role> getAllRoles(){return roleRepository.findAll();}

}
