package br.com.unilab.demo.model.entities;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
public class Laboratorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "numero_laboratorio", nullable = false)
    private Long numeroLaboratorio;

    @Column(name = "departamento_laboratorio")
    private String departamentoLaboratorio;

    @Type(ListArrayType.class)
    @Column(name = "ferramentas_disponiveis", columnDefinition = "varchar[]")
    private List<FerramentasDisponiveis> ferramentasDisponiveis;

    @Column(name = "quantidade_notebooks")
    private Integer quantidadeNotebooks;

    @Column(name = "localizacao_lab")
    private String localizacao;

    @Column(name = "capacidade_lab")
    private Integer capacidade;

    @Column(name = "status")
    private Boolean status;


}
