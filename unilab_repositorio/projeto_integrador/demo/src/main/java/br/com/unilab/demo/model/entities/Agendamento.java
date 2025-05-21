package br.com.unilab.demo.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Classe que representa a Entidade Agendamentos do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 19/03/2025
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "agendamentos")
public class Agendamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_agendamento")
    private String data;

    @Column
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_laboratorio")
    private Laboratorio laboratorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor")
    private Professor professor;

}
