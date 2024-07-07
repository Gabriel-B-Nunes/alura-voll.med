package med.voll.api.domain.service.validacoesConsulta;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {

    void validar(AgendamentoConsultaDTO dados);
}
