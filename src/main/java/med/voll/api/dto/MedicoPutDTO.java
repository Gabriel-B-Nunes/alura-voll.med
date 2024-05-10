package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record MedicoPutDTO(@NotNull
                           Long id,
                           String nome,
                           String telefone,
                           EnderecoDTO enderecoDTO) {
}
