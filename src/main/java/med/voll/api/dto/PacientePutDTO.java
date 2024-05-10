package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record PacientePutDTO(@NotNull
                             Long id,
                             String nome,
                             String telefone,
                             String email,
                             EnderecoDTO enderecoDTO) {
}
