package med.voll.api.domain.dto;

import med.voll.api.domain.models.Endereco;
import med.voll.api.domain.models.Especialidade;
import med.voll.api.domain.models.Medico;

public record MedicoDetalhadoDTO(Long id,
                                 String nome,
                                 String email,
                                 String telefone,
                                 String crm,
                                 Especialidade especialidade,
                                 Endereco endereco) {
    public MedicoDetalhadoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
