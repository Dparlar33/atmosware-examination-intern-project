package com.atmosware.managementService.entities;

import com.atmosware.managementService.core.entites.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "organizations")
public class Organization extends BaseEntity {
    private UUID roleId;
    private String name;
    private String email;
    private String password;
}
