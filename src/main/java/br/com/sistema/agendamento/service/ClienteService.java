package br.com.sistema.agendamento.service;

import br.com.sistema.agendamento.dto.ClienteRequestDTO;
import br.com.sistema.agendamento.dto.ClienteResponseDTO;
import br.com.sistema.agendamento.entity.Cliente;
import br.com.sistema.agendamento.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClienteResponseDTO cadastrar(ClienteRequestDTO request) {
        if (repository.existsByEmail(request.email())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email ja cadastrado"
            );
        }

        Cliente cliente = new Cliente();

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        Cliente salvo = repository.save(cliente);

        return converterParaDTO(salvo);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listar(){
        return repository.findAllByAtivoTrue()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id){
        Cliente cliente = buscarClienteAtivo(id);
        return converterParaDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizar(Long id , ClienteRequestDTO request){
        Cliente cliente = buscarClienteAtivo(id);

        if (repository.existsByEmailAndIdNot(request.email(),id)){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email ja cadastrado por outro cliente"
            );
        }

        cliente.setNome(request.nome());
        cliente.setEmail(request.email());
        cliente.setTelefone(request.telefone());

        Cliente atualizado = repository.save(cliente);

        return converterParaDTO(atualizado);
    }

    @Transactional
    public void desativar(Long id){
        Cliente cliente = buscarClienteAtivo(id);

        cliente.setAtivo(false);

        repository.save(cliente);
    }



    private Cliente buscarClienteAtivo(Long id){
        return repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cliente nao encontrado"
                ));
    }

    private ClienteResponseDTO converterParaDTO(Cliente cliente){
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.isAtivo()
        );
    }
}
