package com.concesionario.app;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.concesionario.app.cliente.Cliente;
import com.concesionario.app.cliente.ClienteRepository;
import com.concesionario.app.cliente.ClienteService;
import com.concesionario.app.concesionario.Concesionario;
import com.concesionario.app.concesionario.ConcesionarioRepository;
import com.concesionario.app.exceptions.ClienteException;
import com.concesionario.app.exceptions.SolicitaPruebaException;
import com.concesionario.app.solicitaPrueba.SolicitaPrueba;
import com.concesionario.app.solicitaPrueba.SolicitaPruebaRepository;
import com.concesionario.app.vehiculo.Vehiculo;
import com.concesionario.app.vehiculo.VehiculoRepository;

@SpringBootApplication
public class ConcesionarioSpringV1Application {

	public static void main(String[] args) throws ClienteException, SolicitaPruebaException {
		ApplicationContext context = SpringApplication.run(ConcesionarioSpringV1Application.class, args);
		
		
		/********DESCOMENTAR PRIMERA ITERACION********		
		
		//Creamos los repositorios		
		ClienteRepository repCliente = context.getBean(ClienteRepository.class);
		VehiculoRepository repVehiculo = context.getBean(VehiculoRepository.class);
		ConcesionarioRepository repConcesionario = context.getBean(ConcesionarioRepository.class);
		SolicitaPruebaRepository repSolicitaPrueba = context.getBean(SolicitaPruebaRepository.class);
		
		//Creamos entidades Clientes		
		Cliente c1 = new Cliente(420392, "Hugo", "Fernandez", LocalDate.now());
		Cliente c2 = new Cliente(231234, "Fernando", "Lopez", 94839282, "fernandito@ejemplo.com", LocalDate.now());
		Cliente c3 = new Cliente(342342, "Pedro", "Garcia", 424324243, "pedrito@ejemplo.com", LocalDate.now());
		
		repCliente.save(c1);//Guardamos Cliente 1 
			
		//Creamos entidades Vehiculo
		Vehiculo v1 = new Vehiculo(12345L, "BRL-201", "Audi", "A1", 20000, 2020);
		Vehiculo v2 = new Vehiculo(32432L, "PDS-011", "Mercedes", "C", 45000, 2010);
		Vehiculo v3 = new Vehiculo(99999L, "XSS-969", "Seat", "Ibiza", 2000, 2005);
		
		
		repVehiculo.save(v1); //Guardamos Vehiculo 1
	
		//Conexion ClienteVehiculo
		
		SolicitaPrueba prueba1 = new SolicitaPrueba(LocalDate.now());
		prueba1.setCliente(c1);
		prueba1.setVehiculo(v1);
		
		repSolicitaPrueba.save(prueba1);
		
		//Conexion Concesionario/Vehiculo
		Concesionario con1 = new Concesionario("PepeAuto");
		Set<Vehiculo>vehiculos = new HashSet<>();
		
		v1.setConcesionario(con1);
		v2.setConcesionario(con1);
		repVehiculo.save(v2);
		vehiculos.add(v1);
		vehiculos.add(v2);	
		con1.setVehiculos(vehiculos);
		
		repConcesionario.save(con1);
				
		********FIN PRIMERA ITERACION********/
		
		/*******DESCOMENTAR SEGUNDA ITERACION*******
		
		ClienteService serCliente = context.getBean(ClienteService.class);
		
		Cliente c1 = new Cliente(12345, "Hugo", "Fernandez", LocalDate.now());
		Cliente c2 = new Cliente(43232, "Lorenzo", "Ramos", LocalDate.now());
		Cliente c3 = new Cliente(34345, "Luis", "Lopez", LocalDate.now());
			
		serCliente.guardarCliente(c1);
		serCliente.guardarCliente(c2);
		serCliente.guardarCliente(c3);
		
		//Metodo listar cliente
		for(int i = 0; i<serCliente.listarClientes().size();i++) {
			System.out.println("Nombre: " + serCliente.listarClientes().get(i).getNombre());
		}
		
		//Metodos buscar por nif
		System.out.println("Cliente con nif 12345: " + serCliente.buscarClientePorNif(12345).getNombre());
		System.out.println("Cliente con nif 12345: " + serCliente.findByNif(43232).getNombre());
		
		//Metodo crear usuario
		serCliente.crearCliente(11111, "Pepe", "Bellido", 666777888, "pepe@ejemplo.com", LocalDate.now());
		
		//Metodo actualizar
		serCliente.actualizarCliente(1L, 11111, "Jose Maria", "Ramirez", 754754754, "JM@ejemplo.com");
		
		//Metodo Solicitar Prueba
		serCliente.crearVehiculo(12343L, "234-MVG", "Audi", "A1", 20000, 2001); //Creo un vehiculo
		serCliente.solicitaPrueba(1L, 1L,'N');
		
		//Metodo cambiar asistencia de Prueba
		serCliente.realizarPrueba(1L, 1L);
		serCliente.realizarPrueba(1L, 1L);
		
		*******FIN SEGUNDA ITERACION*******/
		
		ClienteService clienteService = context.getBean(ClienteService.class);
		
		
		System.out.println(clienteService.buscarClientePorId(1L).getNombre());
		
		clienteService.solicitaPrueba(1L, 1L, LocalDate.of(2001, 11, 14));
		clienteService.realizarPrueba(1L, 1L, LocalDate.of(2001, 11, 14));
		
		
	}

}
