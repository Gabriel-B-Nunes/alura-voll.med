package med.voll.api.domain.dto;

import med.voll.api.domain.models.Consulta;

import java.time.LocalDateTime;

public record AgendamentoConsultaDetalhadoDTO(
        Long id,
        Long idMedico,
        Long idPaciente,
        LocalDateTime data) {
    public AgendamentoConsultaDetalhadoDTO(Consulta consulta) {
        this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
