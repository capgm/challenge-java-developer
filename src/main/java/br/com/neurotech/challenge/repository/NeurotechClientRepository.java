package br.com.neurotech.challenge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.neurotech.challenge.entity.NeurotechClient;

@Repository
public interface NeurotechClientRepository extends JpaRepository<NeurotechClient, Long>{
	
	 @Query(value = "SELECT * FROM CLIENTES WHERE AGE BETWEEN 18 AND 25", nativeQuery = true)
	 List<NeurotechClient>  findByJurosFixos();
	 
	 @Query(value = "SELECT * FROM CLIENTES WHERE AGE BETWEEN 21 AND 65 AND INCOME BETWEEN 5000 AND 15000 ", nativeQuery = true)
	 List<NeurotechClient>  findByJurosVariaveis();
	 
	 @Query(value = "SELECT * FROM CLIENTES WHERE AGE > 65 ", nativeQuery = true)
	 List<NeurotechClient> findByConsignado();
	 
	 @Query(value = " SELECT CASE WHEN (INCOME BETWEEN 5000 AND 15000) THEN 'Hatch'  WHEN (INCOME > 8000 AND AGE> 20) THEN 'SUV' ELSE '' END AS TipoCarro	FROM CLIENTES WHERE ID = ?1", nativeQuery = true)
	 Object findByModelo(Long id);	 
	 
	 @Query(value = " SELECT CASE WHEN (AGE BETWEEN 18 AND 25) THEN 'Taxa de 5% a.a'  WHEN (AGE BETWEEN 21 AND 65 AND INCOME BETWEEN 5000 AND 15000) THEN 'Juros Variáveis'  WHEN (AGE > 65 ) THEN 'Consignado' ELSE '' END AS MODALIDADE_CREDITO, CLIENTES.*	FROM CLIENTES", nativeQuery = true)
	 List<Object> findByModalidadeCredito();
	 	 
	 @Query(value = " SELECT * FROM CLIENTES WHERE AGE BETWEEN 23 AND 25 AND INCOME BETWEEN 5000 AND 15000 ", nativeQuery = true)
	 List<NeurotechClient> findByModalidadeExclusiva();
	 
}
