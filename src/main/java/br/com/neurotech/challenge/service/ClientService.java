package br.com.neurotech.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.NeurotechClientRepository;

@Service
public class ClientService {
	
	
	@Autowired
    private NeurotechClientRepository clientRepository;

    public Long saveClient(NeurotechClient client) {
        // Validar se o cliente já existe (opcional)
        if (clientRepository.existsById(client.getId())) {
            throw new RuntimeException("Cliente já existe");
        }
        
        // Validar campos do cliente
        if (client.getName() == null || client.getAge() == null || client.getIncome() == null || client.getAge() < 0) {
            throw new IllegalArgumentException("Campos inválidos para o cliente");
        }
        
        // Salvar cliente
        NeurotechClient savedClient = clientRepository.save(client);
        return savedClient.getId();
    }

    public NeurotechClient getClientById(Long id) {
        return clientRepository.findById(id)
                               .orElse(null);
    }

    public List<NeurotechClient> findByJurosFixos() {
        return clientRepository.findByJurosFixos();
    }

    public List<NeurotechClient> findByJurosVariaveis() {
        return clientRepository.findByJurosVariaveis();
    }
    
    public List<NeurotechClient> findByConsignado() {
        return clientRepository.findByConsignado();
    }
    
    
    public Object findByModelo(Long id) {
        return clientRepository.findByModelo(id);
    }
    
    
    public List<Object> findByModalidadeCredito() {
        return clientRepository.findByModalidadeCredito();
    }
    
    public List<NeurotechClient> findByModalidadeExclusiva() {
        return clientRepository.findByModalidadeExclusiva();
    }    
    
}
