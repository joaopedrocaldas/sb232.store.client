package store.client;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

@Service
@CrossOrigin(origins = "*")
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> list() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList())
                .stream().map(ClientModel::to).toList();
    }

    public Client find(String id) {
        return clientRepository.findById(id).orElse(null).to();
    }

    public void delete(String id) {
        clientRepository.deleteById(id);
    }

    public Client create(Client in) {
        if (in.senha() == null) {
            throw new RuntimeException("senha invalida");
        }
        in.senha(in.senha().trim());
        if (in.senha().length() < 5) {
            throw new RuntimeException("senha invalida");
        }
        try {
            final String hash = hash(in.senha());
            in.hash(hash);
            in.senha(null);
            return clientRepository.save(new ClientModel(in)).to();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Client update(Client in) {
        ClientModel aux = new ClientModel(in);
        aux.setId(in.id());
        return clientRepository.save(aux).to();
    }

    private String hash(String text) throws NoSuchAlgorithmException {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        byte[] encoded = Base64.getEncoder().encode(hash);

        return new String(encoded);

    }
}
