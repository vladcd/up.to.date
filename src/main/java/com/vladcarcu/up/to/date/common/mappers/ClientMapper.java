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
        String name = null;
        if (user instanceof Client client) {
            name = client.getName();
        } else if (user instanceof Doctor doctor) {
            name = doctor.getName();
        }

        return new ClientDTO(user.getId(), user.getUsername(), user.getPassword(), name);
    }

    public Client toClient(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.id());
        client.setUsername(clientDTO.username());
        client.setPassword(clientDTO.password());
        client.setName(clientDTO.name());
        return client;
    }

}
