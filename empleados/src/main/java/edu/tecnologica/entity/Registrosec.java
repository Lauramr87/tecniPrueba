package edu.tecnologica.entity;
// Generated 27-jun-2017 12:00:31 by Hibernate Tools 5.1.4.Final

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Registrosec generated by hbm2java
 */
@Entity
@Table(name = "REGISTROSEC", schema = "HR")
@SequenceGenerator(name="migen", sequenceName="REGISTRO_SEC_SEQ", allocationSize=1, initialValue=0)
public class Registrosec implements java.io.Serializable {

	private BigDecimal id;

	public Registrosec() {
	}

	public Registrosec(BigDecimal id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="migen")
	//Si ponemos GenerationType.AUTO se comporta igual
	//Mira si hay secuencia en cuyo caso genera secuencia
	//Si no hay secuencia busca la tabla
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

}
