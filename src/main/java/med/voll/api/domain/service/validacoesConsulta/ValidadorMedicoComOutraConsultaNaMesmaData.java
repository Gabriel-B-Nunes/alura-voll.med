package med.voll.api.domain.service.validacoesConsulta;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNaMesmaData implements ValidadorAgendamentoDeConsulta{

    private ConsultaRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        var idMedico = dados.idMedico();
        var dataConsulta = dados.data();
        var medicoPossuiConsultaNaMesmaData = repository.existsByMedicoIdAndData(idMedico, dataConsulta);
    }
}
