package br.com.sistema.agendamento.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profissionais")
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome" , nullable = false , length = 100)
    private String nome;

    @Column(name = "email" , nullable = false , unique = true , length = 150)
    private String email;

    @Column(name = "telefone" , nullable = false , length = 20)
    private String telefone;

    @Column(name = "especialidade", nullable = false)
    private String especialidade;

    @Column(name = "ativo" , nullable = false , length = 100)
    private boolean ativo = true;

    public Profissional() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
