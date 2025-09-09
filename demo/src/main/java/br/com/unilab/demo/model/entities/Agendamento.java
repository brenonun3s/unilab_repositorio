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

    @Column(name = "horario")
    private String horario;

    @Column(name = "numero_lab")
    private Integer numeroLaboratorio;

    @Column(name = "professor_resp")
    private String professor;

    @Column(name = "usuario")
    private String usuario;
    
}
