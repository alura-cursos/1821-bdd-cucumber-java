package br.com.alura.leilao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alura.leilao.model.Usuario;

@Repository
public interface UsuarioRepository   extends JpaRepository<Usuario, Long> {

	@Query("SELECT u FROM Usuario u WHERE u.nome = :username")
    public Usuario getUserByUsername(@Param("username") String username);
}
