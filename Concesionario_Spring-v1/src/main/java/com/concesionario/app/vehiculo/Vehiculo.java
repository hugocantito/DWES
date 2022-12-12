package com.concesionario.app.vehiculo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.concesionario.app.concesionario.Concesionario;
import com.concesionario.app.solicitaPrueba.SolicitaPrueba;

@Entity
@Table(name="TABLA_VEHICULO")
public class Vehiculo {
	
	
	
	
	public Vehiculo() {
		super();
	}

	public Vehiculo(Long numeroBastidor, String matricula, String marca, String modelo, Integer precioDeVenta,
			Integer anioFabricacion) {
		super();
		this.numeroBastidor = numeroBastidor;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.precioDeVenta = precioDeVenta;
		this.anioFabricacion = anioFabricacion;
	}

	public Vehiculo(Long numeroBastidor, String matricula, String marca, String modelo, Integer potencia,
			Integer cilindrada, Integer precioDeVenta, Integer anioFabricacion) {
		super();
		this.numeroBastidor = numeroBastidor;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.potencia = potencia;
		this.cilindrada = cilindrada;
		this.precioDeVenta = precioDeVenta;
		this.anioFabricacion = anioFabricacion;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false, unique = true)
	private Long numeroBastidor;
	
	@Column(length = 10, nullable = false, unique = true)
	private String matricula;
	
	@Column(length = 100, nullable = false)
	private String marca;
	
	@Column(length = 100, nullable = false)
	private String modelo;
	
	@Column(length = 10, nullable = true)
	private Integer potencia;
	
	@Column(length = 10, nullable = true)
	private Integer cilindrada;
	
	@Column(nullable = false)
	private Integer precioDeVenta;
	
	@Column(nullable = false)
	private Integer anioFabricacion;
	
	@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.MERGE)
	private List<SolicitaPrueba> solicitaPrueba = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Concesionario concesionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroBastidor() {
		return numeroBastidor;
	}

	public void setNumeroBastidor(Long numeroBastidor) {
		this.numeroBastidor = numeroBastidor;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getPotencia() {
		return potencia;
	}

	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}

	public Integer getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(Integer cilindrada) {
		this.cilindrada = cilindrada;
	}

	public Integer getPrecioDeVenta() {
		return precioDeVenta;
	}

	public void setPrecioDeVenta(Integer precioDeVenta) {
		this.precioDeVenta = precioDeVenta;
	}

	public Integer getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(Integer anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public List<SolicitaPrueba> getSolicitaPrueba() {
		return solicitaPrueba;
	}

	public void setSolicitaPrueba(List<SolicitaPrueba> solicitaPrueba) {
		this.solicitaPrueba = solicitaPrueba;
	}

	public Concesionario getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(Concesionario concesionario) {
		this.concesionario = concesionario;
	}

}
