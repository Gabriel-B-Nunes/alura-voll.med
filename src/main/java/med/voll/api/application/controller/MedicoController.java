package med.voll.api.application.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.MedicoDTO;
import med.voll.api.domain.dto.MedicoDetalhadoDTO;
import med.voll.api.domain.dto.MedicoGetDTO;
import med.voll.api.domain.dto.MedicoPutDTO;
import med.voll.api.domain.models.Medico;
import med.voll.api.domain.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid MedicoDTO medicoDTO, UriComponentsBuilder uriComponentsBuilder) {
        Medico medico = new Medico(medicoDTO);
        repository.save(medico);

        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetalhadoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoGetDTO>> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<MedicoGetDTO> medicoGetDTOList = repository.findAllByAtivoTrue(pageable).map(MedicoGetDTO::new);
        return ResponseEntity.ok(medicoGetDTOList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid MedicoPutDTO medicoPutDTO) {
        Medico medico = repository.getReferenceById(medicoPutDTO.id());
        medico.atualizar(medicoPutDTO);
        return ResponseEntity.ok(new MedicoDetalhadoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.deletar();
        return ResponseEntity.noContent().build();
    }
}
