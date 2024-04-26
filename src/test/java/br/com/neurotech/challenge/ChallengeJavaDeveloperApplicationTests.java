package br.com.neurotech.challenge;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import br.com.neurotech.challenge.controller.NeurotechClientController;
import br.com.neurotech.challenge.entity.NeurotechClient;

@SpringBootTest
@Configuration
class ChallengeJavaDeveloperApplicationTests {


	@BeforeAll
	public static void setUp() {
		// Carga inicial de dados
		List<NeurotechClient> clientes = Arrays.asList(new NeurotechClient("João", 20, 5000.0),
				new NeurotechClient("Maria", 25, 9000.0), new NeurotechClient("Pedro", 22, 7500.0),
				new NeurotechClient("Ana", 40, 8000.0), new NeurotechClient("Carlos", 45, 9000.0),
				new NeurotechClient("Mariana", 28, 5500.0), new NeurotechClient("Fernando", 33, 7000.0),
				new NeurotechClient("Camila", 29, 5800.0), new NeurotechClient("Lucas", 75, 8500.0),
				new NeurotechClient("Juliana", 80, 9500.0));
	}

	@Test
	public void findByConsignado() {
		NeurotechClientController controller = new NeurotechClientController();
		Assertions.assertTrue(ResponseEntity.ok().build().equals(controller.findByConsignado()));
	}

	@Test
	public void findByModelo() {
		NeurotechClientController controller = new NeurotechClientController();
		Assertions.assertTrue(ResponseEntity.ok().build().equals(controller.findByModelo(Long.valueOf("1"))));
	}

	@Test
	public void findByJurosFixos() {
		NeurotechClientController controller = new NeurotechClientController();
		Assertions.assertTrue(ResponseEntity.ok().build().equals(controller.findByJurosFixos()));
	}

	@Test
	public void findByJurosVariaveis() {
		NeurotechClientController controller = new NeurotechClientController();
		Assertions.assertTrue(ResponseEntity.ok().build().equals(controller.findByJurosVariaveis()));
	}

	@Test
	public void findByModalidadeCredito() {
		NeurotechClientController controller = new NeurotechClientController();
		Assertions.assertTrue(ResponseEntity.ok().build().equals(controller.findByModalidadeCredito()));
	}

	@Test
	public void getClientById() {
		NeurotechClientController controller = new NeurotechClientController();
		Assertions.assertTrue(ResponseEntity.ok().build().equals(controller.getClientById(Long.valueOf("1"))));
	}

	// Método para fechar o EntityManager após todos os testes
    @AfterAll
    public static void tearDown() {
    	
    }
}
