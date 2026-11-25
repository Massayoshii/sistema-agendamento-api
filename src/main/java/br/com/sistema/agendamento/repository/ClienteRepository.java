package br.com.sistema.agendamento.repository;

import br.com.sistema.agendamento.dto.ClienteResponseDTO;
import br.com.sistema.agendamento.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Cliente> findByIdAndAtivoTrue(Long id);

    List<Cliente> findAllByAtivoTrue();
}
