package br.com.unilab.demo.service;


import br.com.unilab.demo.model.Usuario;
import br.com.unilab.demo.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    //private static final String ROLE_USER = "ROLE_USER";

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
    }

    //ESTOU COMENTANDO POIS NO MOMENTO, NÃO É NECESSÁRIO CADASTRAR USUÁRIO
    //MAS DEIXO AQUI PARA FUTURAMENTE, CASO SEJA NECESSÁRIO

    /*@Transactional
    public UsuarioResponse cadastrarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByEmail(usuarioDTO.email()).isPresent()) {
            throw new IllegalArgumentException("Email já cadastrado!");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario.setRole(ROLE_USER);

        Usuario salvo = usuarioRepository.save(usuario);
        return new UsuarioResponse(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    @Transactional
    public UsuarioResponse atualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuarioDTO.email())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));

        usuarioExistente.setNome(usuarioDTO.nome());
        usuarioExistente.setSenha(passwordEncoder.encode(usuarioDTO.senha()));

        return new UsuarioResponse(usuarioExistente.getId(), usuarioExistente.getNome(), usuarioExistente.getEmail());
    }

     */


}