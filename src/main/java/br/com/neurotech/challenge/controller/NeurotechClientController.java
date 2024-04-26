package br.com.neurotech.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name= "Cliente", description = "Apis for integração cliente")
public class NeurotechClientController {
    
    @Autowired
    private ClientService clientService;

    @PostMapping("/api/client")
    @Operation(summary = "Cadastro de Cliente")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<Void> createClient(@RequestBody NeurotechClient client, UriComponentsBuilder uriBuilder) {
        if (client.getName() == null || client.getAge() == null || client.getIncome() == null || client.getAge() < 0) {
            return ResponseEntity.badRequest().build();
        }

        Long clientId = clientService.saveClient(client);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/api/client/{id}").buildAndExpand(clientId).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/api/client/{id}")
    @Operation(summary = "Consulta de Cliente pelo ID")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> getClientById(@PathVariable("id") Long id) {
        try {
            // Chamar o serviço para obter o cliente pelo ID
            NeurotechClient client = clientService.getClientById(id);
            
            // Verificar se o cliente foi encontrado
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.notFound().build(); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }

    @GetMapping("/api/client/jurosfixos")
    @Operation(summary = "Consulta de Cliente na condição de juros fixos")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> findByJurosFixos() {
        try {
            // Chamar o serviço para obter o cliente pelo ID
            List<NeurotechClient> colecao = clientService.findByJurosFixos();
            
            // Verificar se o cliente foi encontrado
            if (colecao != null) {
                return ResponseEntity.ok(colecao); 
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }
    
    @GetMapping("/api/client/jurosvariaveis")
    @Operation(summary = "Consulta de Cliente na condição de juros variáveis")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> findByJurosVariaveis() {
        try {
            // Chamar o serviço para obter o cliente pelo ID
        	List<NeurotechClient> colecao = clientService.findByJurosVariaveis();
            
            // Verificar se o cliente foi encontrado
            if (colecao != null ) {
                return ResponseEntity.ok(colecao); 
            } else {
                return ResponseEntity.notFound().build(); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }
    
    
    @GetMapping("/api/client/consignado")
    @Operation(summary = "Consulta de Cliente na condição de emprestimos consignados")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> findByConsignado() {
        try {
            // Chamar o serviço para obter o cliente pelo ID
        	List<NeurotechClient> colecao = clientService.findByConsignado();
            
            // Verificar se o cliente foi encontrado
            if (colecao != null) {
                return ResponseEntity.ok(colecao); 
            } else {
                return ResponseEntity.notFound().build(); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }

    
    @GetMapping("/api/client/modelo/{id}")
    @Operation(summary = "Consulta o Cliente pelo ID e retorna qual tipo de carro ele se enquadra")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> findByModelo(@PathVariable("id") Long id) {
        try {
            // Chamar o serviço para obter o cliente pelo ID
        	Object obj = clientService.findByModelo(id);
            
            // Verificar se o cliente foi encontrado
            if (obj != null) {
                return ResponseEntity.ok(obj); 
            } else {
                return ResponseEntity.notFound().build(); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }
    
    
    @GetMapping("/api/client/modalidadecredito")
    @Operation(summary = "Consulta todos os Clientes e exibe em qual modalidade ele se enquadra")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> findByModalidadeCredito() {
        try {
            // Chamar o serviço para obter o cliente pelo ID
        	List<Object> colecao = clientService.findByModalidadeCredito();
            
            // Verificar se o cliente foi encontrado
            if (colecao != null) {
                return ResponseEntity.ok(colecao); 
            } else {
                return ResponseEntity.notFound().build(); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }
    
    @GetMapping("/api/client/exclusiva")
    @Operation(summary = "Consulta todos os Clientes que se encontra na situação epecifica, todos os clientes entre 23 e 49 anos que possuem Crédito com juros fixos e estão aptos a adquirirem crédito automotivo para veículos do tipo Hatch ")
    @ApiResponse(responseCode = "200", description = "Sucesso")
    public ResponseEntity<?> findByModalidadeExclusiva() {
        try {
            // Chamar o serviço para obter o cliente pelo ID
        	List<NeurotechClient> colecao = clientService.findByModalidadeExclusiva();
            
            // Verificar se o cliente foi encontrado
            if (colecao != null) {
                return ResponseEntity.ok(colecao); 
            } else {
                return ResponseEntity.notFound().build(); 
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter cliente: " + e.getMessage()); // Retorna status 500 Internal Server Error em caso de erro
        }
    }
}
