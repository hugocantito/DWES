package com.concesionario.app.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concesionario.app.exceptions.ClienteException;
import com.concesionario.app.exceptions.SolicitaPruebaException;
import com.concesionario.app.solicitaPrueba.SolicitaPrueba;
import com.concesionario.app.solicitaPrueba.SolicitaPruebaRepository;
import com.concesionario.app.vehiculo.Vehiculo;
import com.concesionario.app.vehiculo.VehiculoRepository;


@Service
public class ClienteService implements ClienteRepository{
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private VehiculoRepository vehiculoRepo;
	
	@Autowired
	private SolicitaPruebaRepository solicitaPruebaRepo;
	
	//CONSTRUCTOR
	public ClienteService(ClienteRepository cr) {
		clienteRepo = cr;
	}
	
	public void guardarCliente(Cliente cliente) {
		clienteRepo.save(cliente);
	}
	
	public List<Cliente> listarClientes(){
		
		List<Cliente> lista = new ArrayList<>();		
		Iterator<Cliente> iter = clienteRepo.findAll().iterator();
		
		while(iter.hasNext()) {
			Cliente cliente = iter.next();
			lista.add(cliente);
		}
		
		return lista;	
	}
	
	public void crearCliente(int nif, String nombre, String apellidos, Integer tlf, String email, LocalDate fechaAlta) throws ClienteException {
		if(buscarClientePorNif(nif) != null) {
			throw new ClienteException("ERROR: El cliente ya existes");
		}else {
			Cliente cliente = new Cliente(nif, nombre, apellidos, tlf, email, fechaAlta);
			clienteRepo.save(cliente);
		}
	}
	
	public void crearVehiculo(Long numeroBastidor, String matricula, String marca, String modelo, Integer precioDeVenta,
			Integer anioFabricacion) {
		if(buscarVehiculoPorMatricula(matricula) == null) {
			Vehiculo vehiculo = new Vehiculo(numeroBastidor, matricula, marca, modelo, precioDeVenta, anioFabricacion);
			vehiculoRepo.save(vehiculo);
		}
	}
	
	public void actualizarCliente(Long ID,int nif, String nombre, String apellidos, Integer tlf, String email)throws ClienteException {
		if(buscarClientePorId(ID) == null) {
			 throw new ClienteException("ERROR: El cliente no existe");
		}else {
			Cliente cliente = buscarClientePorId(ID);
			cliente.setNombre(nombre);
			cliente.setApellidos(apellidos);
			cliente.setTlf(tlf);
			cliente.setEmail(email);
			cliente.setNif(tlf);
			
			clienteRepo.save(cliente);		
			
		}
	}
	
	public Cliente buscarClientePorNif(int nif) {
		Iterator<Cliente> iter = clienteRepo.findAll().iterator();
		
		while(iter.hasNext()) {
			Cliente cliente = iter.next();
			if(cliente.getNif() == nif) {
				return cliente;
			}
		}
		
		return null;
	}
	
	public Cliente buscarClientePorId(Long ID) {
		Iterator<Cliente> iter = clienteRepo.findAll().iterator();
				
		while(iter.hasNext()) {
			Cliente cliente = iter.next();
			if(cliente.getId() == ID) {
				return cliente;
			}
		}
				
		return null;		
	}
	
	public Vehiculo buscarVehiculoPorId(Long ID) {
		Iterator<Vehiculo> iter = vehiculoRepo.findAll().iterator();
				
		while(iter.hasNext()) {
			Vehiculo vehiculo = iter.next();
			if(vehiculo.getId() == ID) {
				return vehiculo;
			}
		}
				
		return null;		
	}
	
	public SolicitaPrueba buscarPruebaPorIdVehiculoCliente(Long idCliente, Long idVehiculo) {
		Iterator<SolicitaPrueba> iter = solicitaPruebaRepo.findAll().iterator();
		
		while(iter.hasNext()) {
			SolicitaPrueba solicitaPrueba = iter.next();
			if(solicitaPrueba.getVehiculo().getId() == idVehiculo && solicitaPrueba.getCliente().getId() == idCliente) {
				return solicitaPrueba;
			}
		}
				
		return null;
	}
	
	public SolicitaPrueba buscarPruebaPorIdVehiculoClienteFecha(Long idCliente, Long idVehiculo, LocalDate fecha) {
		Iterator<SolicitaPrueba> iter = solicitaPruebaRepo.findAll().iterator();
		
		while(iter.hasNext()) {
			SolicitaPrueba solicitaPrueba = iter.next();
			if(solicitaPrueba.getVehiculo().getId() == idVehiculo && solicitaPrueba.getCliente().getId() == idCliente && solicitaPrueba.getFecha().equals(fecha)) {				
				return solicitaPrueba;
			}
		}
				
		return null;
	}
	
	public Vehiculo buscarVehiculoPorMatricula(String matricula) {
		Iterator<Vehiculo> iter = vehiculoRepo.findAll().iterator();
				
		while(iter.hasNext()) {
			Vehiculo vehiculo = iter.next();
			if(vehiculo.getMatricula() == matricula) {
				return vehiculo;
			}
		}
				
		return null;		
	}
	
	public void solicitaPrueba(Long idCliente, Long idVehiculo, Character asistencia) throws SolicitaPruebaException{
		if(buscarClientePorId(idCliente) == null || buscarVehiculoPorId(idVehiculo) == null) {
			throw new SolicitaPruebaException("ERROR: Cliente o Vehiculo no existe");
		}else {
			SolicitaPrueba solicitaPrueba = new SolicitaPrueba(LocalDate.now(), asistencia);
			solicitaPrueba.setCliente(buscarClientePorId(idCliente));
			solicitaPrueba.setVehiculo(buscarVehiculoPorId(idVehiculo));
			solicitaPruebaRepo.save(solicitaPrueba);
		}
	}
	
	public void solicitaPrueba(Long idCliente, Long idVehiculo, LocalDate fecha) throws SolicitaPruebaException{
		if(buscarClientePorId(idCliente) == null || buscarVehiculoPorId(idVehiculo) == null) {
			throw new SolicitaPruebaException("ERROR: Cliente o Vehiculo no existe");
		}else {
			SolicitaPrueba solicitaPrueba = new SolicitaPrueba(fecha);
			solicitaPrueba.setCliente(buscarClientePorId(idCliente));
			solicitaPrueba.setVehiculo(buscarVehiculoPorId(idVehiculo));
			solicitaPruebaRepo.save(solicitaPrueba);
		}
	}
	
	//Crear una prueba si no existe. Si existe, cambia la asistencia a S
	public void realizarPrueba(Long idCliente, Long idVehiculo)throws SolicitaPruebaException{
		if(buscarClientePorId(idCliente) == null || buscarVehiculoPorId(idVehiculo) == null) {
			throw new SolicitaPruebaException("ERROR: Cliente o Vehiculo no existe");
		}else {
			if(buscarPruebaPorIdVehiculoCliente(idCliente, idVehiculo) == null) {
				solicitaPrueba(idCliente, idVehiculo, 'S');
			}else {
				buscarPruebaPorIdVehiculoCliente(idCliente, idVehiculo).setAsistencia('S');
				solicitaPruebaRepo.save(buscarPruebaPorIdVehiculoCliente(idCliente, idVehiculo));
			}
		}
	}
	
	//igual que el de arriba pero con el parametro fecha
	public void realizarPrueba(Long idCliente, Long idVehiculo, LocalDate fecha)throws SolicitaPruebaException{
		if(buscarClientePorId(idCliente) == null || buscarVehiculoPorId(idVehiculo) == null) {
			throw new SolicitaPruebaException("ERROR: Cliente o Vehiculo no existe");
		}else {
			if(buscarPruebaPorIdVehiculoClienteFecha(idCliente, idVehiculo, fecha) == null) {
				solicitaPrueba(idCliente, idVehiculo, fecha);
			}else {
				buscarPruebaPorIdVehiculoClienteFecha(idCliente, idVehiculo, fecha).setAsistencia('S');
				solicitaPruebaRepo.save(buscarPruebaPorIdVehiculoClienteFecha(idCliente, idVehiculo, fecha));
			}
		}
	}

	@Override
	public <S extends Cliente> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Cliente> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepo.findAll();
	}

	@Override
	public Iterable<Cliente> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Cliente entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Cliente> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cliente findByNif(Integer nif) {
		// TODO Auto-generated method stub
		return clienteRepo.findByNif(nif);
	}

	@Override
	public void deleteByNif(Integer nif) {
		// TODO Auto-generated method stub
		clienteRepo.deleteByNif(nif);
	}


}
