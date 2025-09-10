package br.com.unilab.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "agendamentos")
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_agendamento", nullable = false)
    private String data;

    @Column(name = "horario", nullable = false)
    private String horario;

    @Column(name = "numero_lab")
    private Integer numeroLaboratorio;

    @Column(name = "professor_resp")
    private String professor;

    // Muitos agendamentos para um usuário
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

}
