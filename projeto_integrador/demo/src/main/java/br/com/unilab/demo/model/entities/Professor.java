package br.com.unilab.demo.model.entities;

import br.com.unilab.demo.model.enumerators.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    private final static TipoUsuario USUARIO = TipoUsuario.USUARIO;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome_professor", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario = USUARIO;

    @OneToMany(mappedBy = "usuario")
    private List<Agendamento> agendamentos;

}

