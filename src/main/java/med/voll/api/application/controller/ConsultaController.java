package med.voll.api.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.AgendamentoConsultaDTO;
import med.voll.api.domain.dto.CancelamentoConsultaDTO;
import med.voll.api.domain.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid AgendamentoConsultaDTO dados) {
        var dto = service.agendarConsulta(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid CancelamentoConsultaDTO dados) {
        service.cancelarConsulta(dados);
        return ResponseEntity.noContent().build();
    }
}
