package com.mystock.mygestock.repository;

import com.mystock.mygestock.model.Client;
import com.mystock.mygestock.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {
}
