package com.vladcarcu.up.to.date.doctor;

import com.vladcarcu.up.to.date.common.model.Popularity;
import com.vladcarcu.up.to.date.common.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
@Data
@EqualsAndHashCode(of = "id")
public class Doctor extends User {

    private String name;

    @Enumerated(EnumType.STRING)
    private Popularity popularity = Popularity.UNKNOWN;

    public boolean isStar() {
        return popularity.equals(Popularity.STAR);
    }

    public boolean isUnknown() {
        return popularity.equals(Popularity.UNKNOWN);
    }
}
