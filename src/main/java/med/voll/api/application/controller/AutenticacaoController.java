package med.voll.api.application.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.AutenticacaoDTO;
import med.voll.api.domain.dto.TokenDTO;
import med.voll.api.domain.models.Usuario;
import med.voll.api.domain.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid AutenticacaoDTO autenticacaoDTO){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(autenticacaoDTO.login(), autenticacaoDTO.senha());
        Authentication authentication = manager.authenticate(token);

        var tokenJWT = new TokenDTO(tokenService.gerarToken((Usuario) authentication.getPrincipal()));

        return ResponseEntity.ok(tokenJWT);
    }
}
