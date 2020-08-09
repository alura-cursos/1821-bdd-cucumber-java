package br.com.alura.leilao.integration.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.alura.leilao.repositories.LeilaoRepository;
import br.com.alura.leilao.repositories.UsuarioRepository;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test") 
public class LeiloesControllerTest extends TestBase {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	LeilaoRepository leiloes;
	
	@Autowired
	UsuarioRepository usuarios;

	@Test
	public void verificaSePaginaDeLeiloesCarrega() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/leiloes"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Todos leilões")));
	}
	
}
