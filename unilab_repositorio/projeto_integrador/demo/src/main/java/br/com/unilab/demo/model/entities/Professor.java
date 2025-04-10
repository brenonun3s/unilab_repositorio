package br.com.unilab.demo.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * Classe Entidade que representa os professores do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 19/03/2025
 */

@Entity
@Table(name = "professores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome_professor", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, length = 155)
    private String email;

    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "matricula_professor")
    private String matricula;

    @Column(name = "departamento_professor")
    private String departamento;


}

