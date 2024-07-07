package med.voll.api.domain.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.dto.MedicoDTO;
import med.voll.api.domain.dto.MedicoPutDTO;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Medico")
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private boolean ativo;

    public Medico(MedicoDTO medicoDTO) {
        this.nome = medicoDTO.nome();
        this.email = medicoDTO.email();
        this.telefone = medicoDTO.telefone();
        this.crm = medicoDTO.crm();
        this.especialidade = medicoDTO.especialidade();
        this.endereco = new Endereco(medicoDTO.endereco());
        this.ativo = true;
    }

    public void atualizar(@Valid MedicoPutDTO medicoPutDTO) {
        if(medicoPutDTO.nome() != null) {
            this.nome = medicoPutDTO.nome();
        }
        if(medicoPutDTO.telefone() != null) {
            this.telefone = medicoPutDTO.telefone();
        }
        if(medicoPutDTO.enderecoDTO() != null) {
            this.endereco.atualizar(medicoPutDTO.enderecoDTO());
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
