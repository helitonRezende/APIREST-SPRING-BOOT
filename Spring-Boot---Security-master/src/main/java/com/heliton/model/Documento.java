package com.heliton.model;

import java.io.Serializable;
import java.util.Date;

public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idDocumento;
	private long id;
	private String tipoDocumento;
	private String Descricao;
	private Date dataInclusao;
	private Date dataAtualizacao;
	
	public long getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(long idDocumento) {
		this.idDocumento = idDocumento;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public Date getDataInclusao() {
		return dataInclusao;
	}
	public void setDataInlusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}	
}