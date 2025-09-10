package br.com.unilab.demo.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Classe Entidade que representa os Laboratorios do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 19/03/2025
 */

@Entity
@Table(name = "laboratorios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Laboratorio{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_laboratorio", nullable = false)
    private Long numeroLaboratorio;

    @Column(name = "departamento_laboratorio")
    private String departamentoLaboratorio;

    @Column(name = "localizacao_lab")
    private String localizacao;

    @Column(name = "capacidade_lab")
    private Integer capacidade;

    @Column(name = "status")
    private String status;

    @Column
    private String ferramentasDisponiveis;

    @Column
    private String observacao;



}
