package com.vladcarcu.up.to.date.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladcarcu.up.to.date.common.model.UserDTO;

// TODO: TOP 1
public record ClientDTO(@JsonProperty("id") Integer id,
                        @JsonProperty("username") String username,
                        @JsonProperty("password") String password,
                        @JsonProperty("name") String name) implements UserDTO {

}
