package store.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.PostUpdate;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/client")
public class ClientResource {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public List<ClientOut> list() {
        return clientService.list().stream().map(ClientParser::to).toList();
    }

    @GetMapping("/{id}")
    public ClientOut get(@PathVariable(required = true) String id) {
        Client found = clientService.find(id);
        return found == null ? null : ClientParser.to(found);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) String id) {
        clientService.delete(id);
    }

    @PostMapping("/")
    public void create(@RequestBody ClientIn in) {
        clientService.create(ClientParser.to(in));

    }

    @PutMapping("/{id}")
    public void update(@PathVariable(required = true) String id, @RequestBody ClientIn in) {
        Client old = clientService.find(id);
        Client novo = ClientParser.to(in);
        if (old != null) {

            old.name(novo.name())
                    .cpf(novo.cpf())
                    .email(novo.email())
                    .data_nas(novo.data_nas())
                    .senha(novo.senha());

            clientService.update(old);
        } else {
            ResponseEntity.notFound().build();
        }

    }

}
