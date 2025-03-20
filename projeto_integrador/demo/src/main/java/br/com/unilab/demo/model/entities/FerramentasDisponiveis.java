package br.com.unilab.demo.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * Classe de Ferramentas das máquinas do sistema
 *
 * @author -> Breno Nunes -> @github.com/brenonun3s
 * @date 19/03/2025
 */


@Entity
@Table
@Data
public class FerramentasDisponiveis implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_ferramenta")
    private String nome;





}
