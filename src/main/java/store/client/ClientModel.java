package store.client;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "client")
@NoArgsConstructor
@Getter
@Setter
public class ClientModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "hash")
    private String hash;

    @Column(name = "data_nas")
    private java.util.Date data_nas;

    public ClientModel(Client c) {
        this.id = c.id();
        this.name = c.name();
        this.email = c.email();
        this.data_nas = c.data_nas();
        this.cpf = c.cpf();
        this.hash = c.hash();
    }

    public Client to() {
        return Client.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .data_nas(this.data_nas)
                .cpf(this.cpf)
                .hash(this.hash)
                .build();
    }

}
