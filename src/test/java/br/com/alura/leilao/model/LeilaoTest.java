package br.com.alura.leilao.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LeilaoTest {
	
	@Nested
	@DisplayName("Dado um lance valido")
	class UnicoLanceTests {
		
		Leilao leilao = new Leilao("Macbook Pro 15");
        BigDecimal doisMil = new BigDecimal("2000.0");

		@Nested
		@DisplayName("Quando propoe o lance")
		class AssertLanceNormal {
			
			@Test
			@DisplayName("Entao o lance eh aceito")
		    public void deveReceberUmLance() {
				
				leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));

		        assertEquals(1, leilao.getLances().size());
		        assertEquals(doisMil, leilao.getLances().get(0).getValor());
		    }
		}
	}
	
	@Test
    public void naoDeveAceitarUmLanceIgualAoAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        BigDecimal doisMil = new BigDecimal("2000.0");
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));
		leilao.propoe(new Lance(new Usuario("Bill Gates"), doisMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
	
	
	@Test
    public void naoDeveAceitarUmLanceMenorAoAnterior() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        assertEquals(0, leilao.getLances().size());

        BigDecimal doisMil = new BigDecimal("2000.0");
        BigDecimal quaseDoisMil = new BigDecimal("1999.9");

		leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));
		leilao.propoe(new Lance(new Usuario("Bill Gates"), quaseDoisMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
	
    @Test
    public void deveReceberVariosLances() {
    	
    	BigDecimal doisMil = new BigDecimal("2000.0");
    	BigDecimal tresMil = new BigDecimal("3000.0");
    	
        Leilao leilao = new Leilao("Macbook Pro 15");
        leilao.propoe(new Lance(new Usuario("Steve Jobs"), doisMil));
        leilao.propoe(new Lance(new Usuario("Steve Wozniak"), tresMil));

        assertEquals(2, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
        assertEquals(tresMil, leilao.getLances().get(1).getValor());
    }
    
    @Test
    public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
    	BigDecimal doisMil = new BigDecimal("2000.0");
    	BigDecimal tresMil = new BigDecimal("3000.0");
    	
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");

        leilao.propoe(new Lance(steveJobs, doisMil));
        leilao.propoe(new Lance(steveJobs, tresMil));

        assertEquals(1, leilao.getLances().size());
        assertEquals(doisMil, leilao.getLances().get(0).getValor());
    }
    
    @Test
    public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
        Leilao leilao = new Leilao("Macbook Pro 15");
        Usuario steveJobs = new Usuario("Steve Jobs");
        Usuario billGates = new Usuario("Bill Gates");

        leilao.propoe(new Lance(steveJobs, new BigDecimal("2000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("3000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("4000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("5000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("6000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("7000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("8000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("9000.0")));
        leilao.propoe(new Lance(steveJobs, new BigDecimal("10000.0")));
        leilao.propoe(new Lance(billGates, new BigDecimal("11000.0")));

        // deve ser ignorado
        leilao.propoe(new Lance(steveJobs, new BigDecimal("12000.0")));

        assertEquals(10, leilao.getLances().size());
        assertEquals(new BigDecimal("11000.0"), leilao.getLances().get(leilao.getLances().size()-1).getValor());
    }

}
