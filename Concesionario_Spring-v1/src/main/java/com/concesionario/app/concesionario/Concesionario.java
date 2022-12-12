package com.concesionario.app.concesionario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.concesionario.app.vehiculo.Vehiculo;

@Entity
@Table(name="TABLA_CONCESIONARIO")
public class Concesionario {
	
	
	
	public Concesionario() {
		super();
	}

	public Concesionario(String nombre) {
		super();
		this.nombre = nombre;
	}

	public Concesionario(String nombre, String direccionPostal, String email, String horario, Integer tlf,
			Integer anioApertura) {
		super();
		this.nombre = nombre;
		this.direccionPostal = direccionPostal;
		this.email = email;
		this.horario = horario;
		this.tlf = tlf;
		this.anioApertura = anioApertura;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false, unique = true)
	private String nombre;
	
	@Column(length = 200, nullable = true)
	private String direccionPostal;
	
	@Column(length = 200, nullable = true, unique = true)
	private String email;
	
	@Column(length = 50, nullable = true)
	private String horario;
	
	@Column(length = 15, nullable = true)
	private Integer tlf;
	
	@Column(length = 4, nullable = true)
	private Integer anioApertura;
	
	@OneToMany(mappedBy="concesionario", cascade = CascadeType.MERGE)
	private Set<Vehiculo> vehiculos = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccionPostal() {
		return direccionPostal;
	}

	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getTlf() {
		return tlf;
	}

	public void setTlf(Integer tlf) {
		this.tlf = tlf;
	}

	public Integer getAnioApertura() {
		return anioApertura;
	}

	public void setAnioApertura(Integer anioApertura) {
		this.anioApertura = anioApertura;
	}

	public Set<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(Set<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

}
