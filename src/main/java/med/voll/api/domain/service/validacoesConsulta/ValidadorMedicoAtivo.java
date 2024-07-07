package med.voll.api.domain.service.validacoesConsulta;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{

    private MedicoRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        var idMedico = dados.idMedico();

        if(idMedico == null) {
            return;
        }

        var ativo = repository.findAtivoById(idMedico);

        if(!ativo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico excluído");
        }
    }
}
