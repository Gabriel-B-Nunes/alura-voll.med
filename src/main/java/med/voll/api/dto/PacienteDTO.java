package med.voll.api.dto;

import med.voll.api.models.Endereco;

public record PacienteDTO(String nome,
                          String email,
                          String telefone,
                          String cpf,
                          Endereco endereco) {
}
