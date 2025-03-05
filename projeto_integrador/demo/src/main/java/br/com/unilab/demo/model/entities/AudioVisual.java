package br.com.unilab.demo.model.entities;

import br.com.unilab.demo.model.enumerators.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "audiovisual_atendimento")
public class AudioVisual {

    private final static TipoUsuario AUDIOVISUAL = TipoUsuario.AUDIOVISUAL;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_atendente")
    private String nomeAtendente;

    @Column(name = "email_atendimento")
    private String emailAtendimento;

    @Column(name = "telefone_atendimento")
    private String telefoneAtendimento;

    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario = AUDIOVISUAL;

}