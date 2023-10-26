package store.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class ClientParser {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Client to(ClientIn in) {
        try {
            return Client.builder()
                    .name(in.name())
                    .email(in.email())
                    .data_nas(sdf.parse(in.data_nas()))
                    .cpf(in.cpf())
                    .senha(in.senha())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException("Formato da data invalido", e);
        }
    }

    public static ClientOut to(Client c) {
        return new ClientOut(c.id(), c.name(), c.email(), c.cpf(), c.hash(), sdf.format(c.data_nas()));
    }

}
