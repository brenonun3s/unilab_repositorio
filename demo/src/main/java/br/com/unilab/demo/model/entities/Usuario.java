package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "tb_usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome_usuario", nullable = false)
    private String nome;

    @Column(name = "usuario_senha")
    private String senha;

    @Column(name = "email_usuario", nullable = false)
    private String email;

    @Column(name = "roles_usuario", nullable = false)
    private String role ;
}