package com.ymcatpo.app.topapp.userDao;


import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.ymcatpo.app.topapp.entity.user.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
 
    
}