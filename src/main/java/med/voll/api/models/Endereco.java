package med.voll.api.models;

public record Endereco(String logradouro,
                       String bairro,
                       String cep,
                       String cidade,
                       String uf,
                       String numero,
                       String complemento) {
}
