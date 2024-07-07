package med.voll.api.domain.service;

import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.dto.AgendamentoConsultaDetalhadoDTO;
import med.voll.api.domain.dto.CancelamentoConsultaDTO;
import med.voll.api.domain.models.Medico;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.service.validacoesConsulta.ValidadorAgendamentoDeConsulta;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.domain.models.Consulta;
import med.voll.api.domain.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public AgendamentoConsultaDetalhadoDTO agendarConsulta(AgendamentoConsultaDTO dados) {
        if(!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("id do paciente informado não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);

        var paciente = repository.getReferenceById(dados.idPaciente()).getPaciente();
        var consulta = new Consulta(paciente, medico, dados.data());
        repository.save(consulta);

        return new AgendamentoConsultaDetalhadoDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dados) {
        if(dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("especialidade é obrigatório quando id do médico não for informado");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelarConsulta(CancelamentoConsultaDTO dados) {
        if(!repository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("id da consulta informado não existe");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
