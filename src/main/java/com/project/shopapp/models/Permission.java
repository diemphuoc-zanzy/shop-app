package com.project.shopapp.models;

import com.project.shopapp.common.PERMISSION_ACTION;
import com.project.shopapp.models.base.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
@Entity
public class Permission extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(length = 100)
    private String resource;

    @Column(nullable = false, length = 50)
    private String method;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private PERMISSION_ACTION action;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}