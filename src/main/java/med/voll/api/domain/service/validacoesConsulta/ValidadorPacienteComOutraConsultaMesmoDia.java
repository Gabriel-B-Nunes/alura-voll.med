package med.voll.api.domain.service.validacoesConsulta;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteComOutraConsultaMesmoDia implements ValidadorAgendamentoDeConsulta{

    private ConsultaRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var idPaciente = dados.idPaciente();
        var pacientePossuiOutraConsultaNoDia = repository.existsByPacienteIdAndDataBetween(idPaciente, primeiroHorario, ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
