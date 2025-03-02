package br.com.unilab.demo.entities;

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

    @Column(name = "usuario_agendado")
    private String usuarioAgendado;

    @Column(name = "data_agendamento")
    private LocalDateTime dataAgendamento;

    @Column(name = "numero_laboratorio")
    private int numeroLaboratorio;






}
