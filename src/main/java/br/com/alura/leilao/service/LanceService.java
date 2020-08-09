package br.com.alura.leilao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.mudi.dto.NovoLanceDto;
import br.com.alura.leilao.repositories.LanceRepository;
import br.com.alura.leilao.repositories.LeilaoRepository;
import br.com.alura.leilao.repositories.UsuarioRepository;

@Service
public class LanceService {

	@Autowired
	public LanceRepository lancesRepo;
	
	@Autowired
	public UsuarioRepository usuariosRepo;
	
	@Autowired
	public LeilaoRepository leiloesRepo;

	public boolean propoeLance(NovoLanceDto lanceDto, String nomeUsuario) {

		Usuario usuario = usuariosRepo.getUserByUsername(nomeUsuario);
		Lance lance = lanceDto.toLance(usuario);

		Leilao leilao =  this.getLeilao(lanceDto.getLeilaoId());

		if (leilao.propoe(lance)) {
			lancesRepo.save(lance);
			return true;
		}
		
		return false;
	}

	public Leilao getLeilao(Long leilaoId) {
		return leiloesRepo.getOne(leilaoId);
	}
}