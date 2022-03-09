package com.madirex.hairsalonserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "login")
@ToString
public class Login {
    private String id;
    private String token;
    private Date instant;
    @ToString.Exclude
    private User user;

    public Login(String token, Date instant, User user) {
        this.id = UUID.randomUUID().toString();
        this.token = token;
        this.instant = instant;
        this.user = user;
    }

    public Login(String token, User user) {
        this.id = UUID.randomUUID().toString();
        this.token = token;
        this.instant = Date.from(Instant.now());
        this.user = user;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank(message = "El token no puede estar vacío")
    @Column(length = 512)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NotNull(message = "El instante no puede ser nulo")
    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instance) {
        this.instant = instance;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @NotNull(message = "El usuario no puede ser nulo")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
