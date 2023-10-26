package store.client;

public record ClientOut(
                String id,
                String name,
                String email,
                String cpf,
                String hash,
                String data_nas) {
}