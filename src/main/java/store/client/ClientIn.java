package store.client;

public record ClientIn(
                String name,
                String email,
                String data_nas,
                String cpf,
                String senha) {
}
