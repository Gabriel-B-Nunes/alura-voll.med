package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.MedicoDTO;
import med.voll.api.dto.MedicoGetDTO;
import med.voll.api.dto.MedicoPutDTO;
import med.voll.api.models.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid MedicoDTO medicoDTO) {
        repository.save(new Medico(medicoDTO));
    }

    @GetMapping
    public Page<MedicoGetDTO> listar(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(MedicoGetDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid MedicoPutDTO medicoPutDTO) {
        Medico medico = repository.getReferenceById(medicoPutDTO.id());
        medico.atualizar(medicoPutDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        medico.deletar();
    }
}
