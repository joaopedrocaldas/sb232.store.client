package store.client;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class Client {
    private String id;
    private String name;
    private java.util.Date data_nas;
    private String cpf;
    private String email;
    private String senha;
    private String hash;
}
