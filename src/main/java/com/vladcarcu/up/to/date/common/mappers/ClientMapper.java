package com.vladcarcu.up.to.date.common.mappers;

import com.vladcarcu.up.to.date.client.Client;
import com.vladcarcu.up.to.date.client.ClientDTO;
import com.vladcarcu.up.to.date.common.model.User;
import com.vladcarcu.up.to.date.doctor.Doctor;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    // TODO: TOP 4
    public ClientDTO toDTO(User user) {
        ClientDTO.ClientDTOBuilder builder = ClientDTO.builder()
                .id(user.getId())
                .username(user.getUsername());
        if (user instanceof Client) {
            Client client = (Client) user;
            builder.name(client.getName());
        } else if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            builder.name(doctor.getName());
        }
        return builder.build();
    }

    public Client toClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setUsername(clientDTO.getUsername());
        client.setPassword(clientDTO.getPassword());
        client.setName(clientDTO.getName());
        return client;
    }

}
