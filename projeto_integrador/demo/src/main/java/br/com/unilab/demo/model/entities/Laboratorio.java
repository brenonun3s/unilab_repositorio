package br.com.unilab.demo.model.entities;

import br.com.unilab.demo.model.enumerators.DepartamentoLaboratorio;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import java.util.List;

@Entity
@Table(name = "/laboratorios")
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

    @Column(name = "status_laboratorio", nullable = false)
    private boolean statusLaboratorio;

    @Column(name = "departamento_laboratorio")
    private DepartamentoLaboratorio departamentoLaboratorio;

    @Type(ListArrayType.class)
    @Column(name = "ferramentas_disponiveis", columnDefinition = "varchar[]")
    private List<String> ferramentasDisponiveis;

    @Column(name = "quantidade_notebooks")
    private Integer quantidadeNotebooks;

    @OneToMany(mappedBy = "laboratorio")
    private List<Agendamento> agendamentos;

}
