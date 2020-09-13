package com.vladcarcu.up.to.date.client;

import com.vladcarcu.up.to.date.common.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @PostMapping
    public ClientDTO createClient(@RequestBody ClientDTO clientDTO) {
        Client client = clientMapper.toClient(clientDTO);
        return clientMapper.toDTO(clientService.createClient(client));
    }
}
