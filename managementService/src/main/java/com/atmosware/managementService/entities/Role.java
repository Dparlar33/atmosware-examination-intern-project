package com.atmosware.managementService.entities;

import com.atmosware.managementService.core.entites.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name="roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity implements GrantedAuthority {

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<UserRole> userRoles;

    @Override
    public String getAuthority() {
        return this.name.toLowerCase();
    }
}
