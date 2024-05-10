package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.PacienteDTO;
import med.voll.api.dto.PacienteGetDTO;
import med.voll.api.dto.PacientePutDTO;
import med.voll.api.models.Paciente;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PacienteDTO pacienteDTO) {
        repository.save(new Paciente(pacienteDTO));
    }

    @GetMapping
    public Page<PacienteGetDTO> listar (@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(PacienteGetDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody PacientePutDTO pacientePutDTO) {
        Paciente paciente = repository.getReferenceById(pacientePutDTO.id());
        paciente.atualizar(pacientePutDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id) {
        Paciente paciente = repository.getReferenceById(id);
        paciente.deletar();
    }
}
