package com.vladcarcu.up.to.date.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladcarcu.up.to.date.common.model.UserDTO;

public record DoctorDTO(@JsonProperty("id") Integer id,
                        @JsonProperty("username") String username,
                        @JsonProperty("password") String password,
                        @JsonProperty("name") String name,
                        @JsonProperty("popularity") String popularity) implements UserDTO {
}
