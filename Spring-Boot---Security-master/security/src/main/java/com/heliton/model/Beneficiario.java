package com.heliton.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Beneficiario implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String nome;
	private String telefone;
	private Date dataNascimento;
	private Date dataInclusao;
	private Date dataAtualizacao;
	
	private List<Documento> documento;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	public List<Documento> getDocumento() {
		return documento;
	}
	public void setDocumento(List<Documento> documento) {
		this.documento = documento;
	}	
}
