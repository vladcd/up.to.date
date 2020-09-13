package com.vladcarcu.up.to.date.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@EqualsAndHashCode(of = {"id", "username"})
public abstract class User {

    @Id
    @GeneratedValue
    protected Integer id;

    private String username;

    private String password;
}
