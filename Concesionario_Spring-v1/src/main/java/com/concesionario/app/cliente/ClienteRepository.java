package com.concesionario.app.cliente;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	@Transactional
	Cliente findByNif(Integer nif);
	
	@Transactional
	void deleteByNif(Integer nif);
	
	
}
