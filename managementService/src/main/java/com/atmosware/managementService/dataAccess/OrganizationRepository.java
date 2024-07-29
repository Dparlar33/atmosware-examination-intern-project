package com.atmosware.managementService.dataAccess;

import com.atmosware.managementService.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.rmi.server.UID;

public interface OrganizationRepository extends JpaRepository<Organization, UID> {
}
