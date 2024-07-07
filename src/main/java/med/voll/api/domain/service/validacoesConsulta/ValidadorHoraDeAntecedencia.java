package med.voll.api.domain.service.validacoesConsulta;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHoraDeAntecedencia implements ValidadorAgendamentoDeConsulta{

    public void validar(AgendamentoConsultaDTO dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
        }
    }
}
