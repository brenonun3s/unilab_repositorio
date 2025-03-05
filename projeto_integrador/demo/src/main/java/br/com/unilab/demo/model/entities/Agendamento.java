package br.com.unilab.demo.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_agendamento")
    private LocalDateTime dataAgendamento;

    @ManyToOne
    @JoinColumn(name = "laboratorio_agendado")
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "professor")
    private Professor professor;


}
