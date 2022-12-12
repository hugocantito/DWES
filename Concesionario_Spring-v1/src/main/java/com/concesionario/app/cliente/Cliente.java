package com.concesionario.app.cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.concesionario.app.solicitaPrueba.SolicitaPrueba;

@Entity
@Table(name="TABLA_CLIENTE")
public class Cliente {

/** Constructores: **/
	
	/**Vacio**/	
	public Cliente() {
		super();
	}
	
	/**Todos**/
	public Cliente(Integer nif, String nombre, String apellidos, Integer tlf, String email, LocalDate fechaAlta) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.tlf = tlf;
		this.email = email;
		this.fechaAlta = fechaAlta;
	}
	
	/**Solo Obligatorios**/
	public Cliente(Integer nif, String nombre, String apellidos, LocalDate fechaAlta) {
		super();
		this.nif = nif;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaAlta = fechaAlta;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	@Column(length = 9, nullable = false, unique = true)
	private Integer nif;
	
	@Column(length = 30, nullable = false)
	private String nombre;
	
	@Column(length = 60, nullable = false)
	private String apellidos;
	
	@Column(length = 250, nullable = true)
	private Integer tlf;
	
	@Column(nullable = true, unique = true)
	private String email;
	
	@Column(nullable = false)
	private LocalDate fechaAlta;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.MERGE)
	private List<SolicitaPrueba> solicitaPrueba = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNif() {
		return nif;
	}

	public void setNif(Integer nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getTlf() {
		return tlf;
	}

	public void setTlf(Integer tlf) {
		this.tlf = tlf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public List<SolicitaPrueba> getSolicitaPrueba() {
		return solicitaPrueba;
	}

	public void setSolicitaPrueba(List<SolicitaPrueba> solicitaPrueba) {
		this.solicitaPrueba = solicitaPrueba;
	}
	
}
