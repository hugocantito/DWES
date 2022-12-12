package com.concesionario.app.solicitaPrueba;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.concesionario.app.cliente.Cliente;
import com.concesionario.app.vehiculo.Vehiculo;

@Entity
@Table(name="TABLA_SOLICITA_PRUEBA")
public class SolicitaPrueba {
	
	
	
	public SolicitaPrueba() {
		super();
	}

	public SolicitaPrueba(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	public SolicitaPrueba(LocalDate fecha, Character asistencia) {
		super();
		this.fecha = fecha;
		this.asistencia = asistencia;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate fecha;
	
	@Column(nullable = true)
	private Character asistencia;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
	private Vehiculo vehiculo;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Character getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Character asistencia) {
		this.asistencia = asistencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
