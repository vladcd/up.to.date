package com.vladcarcu.up.to.date.client;

import com.vladcarcu.up.to.date.common.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: TOP 1
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String name;
}
