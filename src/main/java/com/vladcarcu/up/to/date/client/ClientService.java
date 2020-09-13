package com.vladcarcu.up.to.date.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public Client createClient(Client client) {
        // no matter what comes from the request, this method needs to create a new client
        client.setId(null);
        return clientRepository.save(client);
    }

}
