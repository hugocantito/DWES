package com.concesionario.app.cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.concesionario.app.exceptions.ClienteException;
import com.concesionario.app.exceptions.SolicitaPruebaException;

@RestController
@RequestMapping("clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienterService;
	
	//Buscar todos los clientes Ejemplo: "/clientes"
	@GetMapping
	public ResponseEntity<Iterable<Cliente>> findAll(){
		return new ResponseEntity<>(clienterService.findAll(), HttpStatus.OK);
	}
	
	//Buscar cliente por NIF Ejemplo: "/clientes/12345"
	@GetMapping("/{nif}")
	public ResponseEntity<Cliente> findClientByNif(@PathVariable int nif){
		return new ResponseEntity<>(clienterService.buscarClientePorNif(nif), HttpStatus.OK);
	}
	
	//Eliminar cliente por NIF Ejemplo: "/cliente/12345"
	@DeleteMapping("/{nif}")
	public ResponseEntity<Void> deleteClienteByNif(@PathVariable int nif){
		clienterService.deleteByNif(nif);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Crear Usuario a partir del body
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente c) throws ClienteException{
		clienterService.crearCliente(c.getNif(), c.getNombre(), c.getApellidos(), c.getTlf(), c.getEmail(), LocalDate.now());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> update(@RequestBody Cliente c) throws ClienteException{
		clienterService.actualizarCliente(c.getId(), c.getNif(), c.getNombre(), c.getApellidos(), c.getTlf(), c.getEmail());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/{idCliente}/{idVehiculo}/{dia}/{mes}/{año}")
	public ResponseEntity<Void> createSolicitaPrueba(@PathVariable Long idCliente, @PathVariable Long idVehiculo,
			@PathVariable int dia, @PathVariable int mes, @PathVariable int año) throws SolicitaPruebaException{
		clienterService.solicitaPrueba(1L, 1L, LocalDate.of(año, mes, dia));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/{idCliente}/{idVehiculo}/{dia}/{mes}/{año}")
	public ResponseEntity<Void> realizedSolicitaPrueba(@PathVariable Long idCliente, @PathVariable Long idVehiculo,
		@PathVariable int dia, @PathVariable int mes, @PathVariable int año) throws SolicitaPruebaException{
		clienterService.realizarPrueba(idCliente, idVehiculo, LocalDate.of(año, mes, dia));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	

}
