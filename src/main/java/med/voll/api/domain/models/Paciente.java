package med.voll.api.domain.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.dto.PacienteDTO;
import med.voll.api.domain.dto.PacientePutDTO;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Paciente(PacienteDTO pacienteDTO) {
        this.nome = pacienteDTO.nome();
        this.email = pacienteDTO.email();
        this.telefone = pacienteDTO.telefone();
        this.cpf = pacienteDTO.cpf();
        this.endereco = new Endereco(pacienteDTO.endereco());
        this.ativo = true;
    }

    public void atualizar(PacientePutDTO pacientePutDTO) {
        if (pacientePutDTO.nome() != null) {
            this.nome = pacientePutDTO.nome();
        }
        if (pacientePutDTO.telefone() != null) {
            this.telefone = pacientePutDTO.telefone();
        }
        if (pacientePutDTO.email() != null) {
            this.email = pacientePutDTO.email();
        }
        if (pacientePutDTO.enderecoDTO() != null) {
            this.endereco.atualizar(pacientePutDTO.enderecoDTO());
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
