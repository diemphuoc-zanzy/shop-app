package com.project.shopapp.models;

import com.project.shopapp.models.base.BaseModel;
import com.project.shopapp.services.implement.TOKEN_TYPE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tokens")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Token extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", length = 300)
    private String token;

    @Column(name = "token_type", length = 50)
    @Enumerated(EnumType.STRING)
    private TOKEN_TYPE tokenType;

    @Column(name = "expiration_date", length = 50)
    private Long expirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Token(String token, TOKEN_TYPE tokenType, Long expirationDate, User user) {
        this.token = token;
        this.tokenType = tokenType;
        this.expirationDate = expirationDate;
        this.user = user;
    }

}
