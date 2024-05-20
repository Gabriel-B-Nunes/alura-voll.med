package med.voll.api.domain.dto;

import med.voll.api.domain.models.Especialidade;
import med.voll.api.domain.models.Medico;

public record MedicoGetDTO (String nome, String email, String crm, Especialidade especialidade){

    public MedicoGetDTO (Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
