package med.voll.api.dto;

import med.voll.api.models.Paciente;

public record PacienteGetDTO(String nome, String email, String telefone) {

    public PacienteGetDTO(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
    }
}