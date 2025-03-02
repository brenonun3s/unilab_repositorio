package br.com.unilab.demo.repositories;

import br.com.unilab.demo.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
