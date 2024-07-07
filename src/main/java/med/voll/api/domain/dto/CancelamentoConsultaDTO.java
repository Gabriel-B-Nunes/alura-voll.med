package med.voll.api.domain.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.models.MotivoCancelamento;

public record CancelamentoConsultaDTO(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo) {
}
