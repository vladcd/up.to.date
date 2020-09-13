package com.vladcarcu.up.to.date.doctor;

import com.vladcarcu.up.to.date.common.model.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTO implements UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String popularity;
}
