package med.voll.api.domain.service.validacoesConsulta;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{

    private PacienteRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        var idPaciente = dados.idPaciente();
        var ativo = repository.findAtivoById(idPaciente);

        if(!ativo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
