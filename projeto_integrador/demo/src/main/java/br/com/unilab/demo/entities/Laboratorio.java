package br.com.unilab.demo.entities;

import br.com.unilab.demo.entities.enumeradores.DepartamentoLaboratorio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "laboratorios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_laboratorio", nullable = false)
    private Long numeroLaboratorio;

    @Column(name = "laboratorio_disponivel", nullable = false)
    private boolean laboratorioDisponivel;

    @Column(name = "departamento_laboratorio")
    private DepartamentoLaboratorio departamentoLaboratorio;

    @Column(name = "ferramentas_disponiveis",nullable = false)
    @ElementCollection
    private List<String> ferramentasDisponiveis;

    @Column(name = "quantidade_notebooks")
    private Integer quantidadeNotebooks;

}
