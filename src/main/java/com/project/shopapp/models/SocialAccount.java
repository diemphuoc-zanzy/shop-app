package com.project.shopapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "social_accounts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "provider", length = 20)
    private String provider;

    @Column (name = "provider_id", length = 50)
    private String providerId;

    @Column (length = 150)
    private String email;

    @Column (length = 150)
    private String name;
}
