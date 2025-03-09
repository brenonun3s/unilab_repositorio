package br.com.unilab.demo.model.entities;

import br.com.unilab.demo.model.enumerators.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "professores")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome_professor", nullable = false, length = 150)
    private String nome;

    @Column(name = "email", nullable = false, length = 155)
    private String email;

    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "senha", nullable = false, length = 20)
    private String senha;

    //@OneToMany(mappedBy = "usuario")
    //private List<Agendamento> agendamentos;

}

