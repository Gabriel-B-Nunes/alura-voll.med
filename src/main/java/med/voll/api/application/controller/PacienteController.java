package med.voll.api.application.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.PacienteDTO;
import med.voll.api.domain.dto.PacienteDetalhadoDTO;
import med.voll.api.domain.dto.PacienteGetDTO;
import med.voll.api.domain.dto.PacientePutDTO;
import med.voll.api.domain.models.Paciente;
import med.voll.api.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO, UriComponentsBuilder uriComponentsBuilder) {
        Paciente paciente = new Paciente(pacienteDTO);
        repository.save(paciente);

        URI uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new PacienteDetalhadoDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<PacienteGetDTO>> listar (@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<PacienteGetDTO> pacienteGetDTOPage = repository.findAllByAtivoTrue(pageable).map(PacienteGetDTO::new);

        return ResponseEntity.ok(pacienteGetDTOPage);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody PacientePutDTO pacientePutDTO) {
        Paciente paciente = repository.getReferenceById(pacientePutDTO.id());
        paciente.atualizar(pacientePutDTO);

        return ResponseEntity.ok(new PacienteDetalhadoDTO(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.deletar();

        return ResponseEntity.noContent().build();
    }
}
