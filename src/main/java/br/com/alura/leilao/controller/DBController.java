package br.com.alura.leilao.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.repositories.LanceRepository;
import br.com.alura.leilao.repositories.LeilaoRepository;
import br.com.alura.leilao.repositories.UsuarioRepository;

@RestController
@RequestMapping("/db")
@Profile("test")
public class DBController {
	
	@Autowired
	private LanceRepository lances;

	@Autowired
	private UsuarioRepository usuarios;
	
	@Autowired
	private LeilaoRepository leiloes;
	
	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/db")
	public ResponseEntity<String> deleta() {
		lances.deleteAll();
		leiloes.deleteAll();
		usuarios.deleteAll();
		return ResponseEntity.ok("{'message':'dados removidos'}");
	}
	
	@GetMapping("/seed")
	public ResponseEntity<String> popula() {
		
		lances.deleteAll();
		leiloes.deleteAll();
		usuarios.deleteAll();
		
		Usuario fulano = new Usuario("fulano", "fulano@gmail.com", encoder.encode("pass"));
		fulano.setRole("ROLE_USER");
		fulano.activa();
		
		Usuario beltrano = new Usuario("beltrano", "beltrano@gmail.com", encoder.encode("pass"));
		beltrano.setRole("ROLE_USER");
		beltrano.activa();
		
		Usuario cigano = new Usuario("cigano", "cigano@gmail.com", encoder.encode("pass"));
		cigano.setRole("ROLE_ADMIN");
		cigano.activa();
		
		Leilao leilaoTablet = new Leilao("Tablet Xpto 3",new BigDecimal("5.0"), fulano);
		
		Lance lance10 = new Lance(beltrano, BigDecimal.TEN);
		Lance lance15 = new Lance(cigano, new BigDecimal("15.0"));
		
		leilaoTablet.propoe(lance10);
		leilaoTablet.propoe(lance15);
		
		usuarios.save(fulano);
		usuarios.save(beltrano);
		usuarios.save(cigano);

		leiloes.save(leilaoTablet);

		lances.save(lance10);
		lances.save(lance15);
		
		Leilao leilaoSemLance = new Leilao("Computador Z3", new BigDecimal("500.0"), beltrano);
		leiloes.save(leilaoSemLance);


		return ResponseEntity.ok("{'message':'dados populadas'}");
	}
	
	
}






