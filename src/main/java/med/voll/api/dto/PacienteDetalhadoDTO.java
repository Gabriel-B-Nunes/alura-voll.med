package med.voll.api.dto;

import med.voll.api.models.Endereco;
import med.voll.api.models.Paciente;

public record PacienteDetalhadoDTO(Long id,
                                   String nome,
                                   String email,

                                   String telefone,
                                   String cpf,
                                   Endereco endereco) {
    public PacienteDetalhadoDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
