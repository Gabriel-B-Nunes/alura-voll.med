package med.voll.api.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.EnderecoDTO;
import med.voll.api.dto.MedicoPutDTO;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Endereco(EnderecoDTO enderecoDTO) {
        this.logradouro = enderecoDTO.logradouro();
        this.bairro = enderecoDTO.bairro();
        this.cep = enderecoDTO.cep();
        this.cidade = enderecoDTO.cidade();
        this.uf = enderecoDTO.uf();
        this.numero = enderecoDTO.numero();
        this.complemento = enderecoDTO.complemento();
    }

    public void atualizar(EnderecoDTO enderecoDTO) {
        if (enderecoDTO.logradouro() != null) {
            this.logradouro = enderecoDTO.logradouro();
        }
        if (enderecoDTO.bairro() != null) {
            this.bairro = enderecoDTO.bairro();
        }
        if (enderecoDTO.cep() != null) {
            this.cep = enderecoDTO.cep();
        }
        if (enderecoDTO.cidade() != null) {
            this.cidade = enderecoDTO.cidade();
        }
        if (enderecoDTO.uf() != null) {
            this.uf = enderecoDTO.uf();
        }
        if (enderecoDTO.numero() != null) {
            this.numero = enderecoDTO.numero();
        }
        if (enderecoDTO.complemento() != null) {
            this.complemento = enderecoDTO.complemento();
        }
    }
}
