package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the configuracaosmc database table.
 * 
 */
@Entity
@Table(name="configuracaosmc")
public class Configuracaosmc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONFIGURACAOSMC_CODIGOCONFIGURACAOSMC_GENERATOR", sequenceName="CONFIGURACAOSMC_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONFIGURACAOSMC_CODIGOCONFIGURACAOSMC_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoconfiguracaosmc;

	private Boolean sincronizado;

    public Configuracaosmc() {
    }

	public Integer getCodigoconfiguracaosmc() {
		return this.codigoconfiguracaosmc;
	}

	public void setCodigoconfiguracaosmc(Integer codigoconfiguracaosmc) {
		this.codigoconfiguracaosmc = codigoconfiguracaosmc;
	}

	public Boolean getSincronizado() {
		return this.sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

}