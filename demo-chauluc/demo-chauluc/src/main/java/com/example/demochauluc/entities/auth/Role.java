package com.example.demochauluc.entities.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demochauluc.entities.AbstractAuditingEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role extends AbstractAuditingEntity<Long>{

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String role;
}
