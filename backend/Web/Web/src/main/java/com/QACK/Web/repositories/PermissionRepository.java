package com.QACK.Web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.QACK.Web.Model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{}