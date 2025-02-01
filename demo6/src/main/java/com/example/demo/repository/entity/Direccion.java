package com.example.demo.repository.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "direcciones")
public class Direccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String descripcion;
	private String pais;
	private String cp;
	@Column(name = "fechaalta")
	private Date fechaAlta;
	// la fecha no se esta cogiendo bien, no se muestra en las vistas

//	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "listaDirecciones")
//	@ToString.Exclude
//	private List<Cliente> listaClientes;

//	@ManyToMany(cascade = { CascadeType.ALL }, mappedBy = "listaDirecciones")
//	@ToString.Exclude
//	private Set<Cliente> listaClientes;

	// Mapeo con la entidad ClienteDireccion con Set
	// IMPORTATE: Poner a LAZY la relacion en este caso, ya que es la relacion N a N
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "direccion")
	@ToString.Exclude
	private Set<ClienteDireccion> listaClientesDirecciones;

	public Direccion() {
		super();
//		this.listaClientes = new HashSet<Cliente>();
		this.listaClientesDirecciones = new HashSet<ClienteDireccion>();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
