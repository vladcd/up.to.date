package com.vladcarcu.up.to.date.client;

import com.vladcarcu.up.to.date.common.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
@EqualsAndHashCode(of = "id")
public class Client extends User {

    private String name;

}
