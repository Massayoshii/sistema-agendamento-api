package br.com.sistema.agendamento.controller;

import br.com.sistema.agendamento.dto.ClienteRequestDTO;
import br.com.sistema.agendamento.dto.ClienteResponseDTO;
import br.com.sistema.agendamento.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponseDTO cadastrarCliente(@RequestBody
                                               @Valid ClienteRequestDTO request){
        return service.cadastrar(request);
    }

    @GetMapping
    public List<ClienteResponseDTO> listarClientes(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public ClienteResponseDTO buscarPorId(@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ClienteResponseDTO atualizarCliente(@PathVariable Long id,
                                        @Valid ClienteRequestDTO request){
        return service.atualizar(id , request);
    }

    @PatchMapping("/{id}/desativar")
    public void desativarCliente(@PathVariable Long id){
        service.desativar(id);
    }
}
