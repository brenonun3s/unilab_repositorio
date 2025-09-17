package br.com.unilab.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Table(name = "tb_usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nome;

    @Column(name = "usuario_senha")
    private String senha;

    @Column(name = "email_usuario", nullable = false)
    private String email;

    @Column(name = "roles_usuario", nullable = false)
    private String role;

    // Um usuário pode ter vários agendamentos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos;
}
