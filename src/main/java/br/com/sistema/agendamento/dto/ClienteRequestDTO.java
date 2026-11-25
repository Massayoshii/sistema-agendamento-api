package br.com.sistema.agendamento.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteRequestDTO(
        @NotBlank(message = "O nome é obrigatorio")
        @Size(max = 100 , message = "O nome deve ter no maximo 100 caracteres")
        String nome,

        @Email(message = "Informe um email valido")
        @NotBlank(message = "O email é obrigatorio")
        @Size(max = 150)
        String email,

        @Size(max = 20 , message = "O telefone deve ter no maximo 20 caracteres")
        String telefone
) {
}
