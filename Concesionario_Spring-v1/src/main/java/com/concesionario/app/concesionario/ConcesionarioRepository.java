package com.concesionario.app.concesionario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcesionarioRepository extends CrudRepository<Concesionario, Long>{

}
