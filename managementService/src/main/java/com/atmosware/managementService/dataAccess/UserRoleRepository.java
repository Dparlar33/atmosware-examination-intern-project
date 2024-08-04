package com.atmosware.managementService.dataAccess;

import com.atmosware.managementService.entities.User;
import com.atmosware.managementService.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
    UserRole findUserRoleByUserId(UUID userId);
}
