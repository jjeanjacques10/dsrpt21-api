package br.com.fiap.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ACAO")
public class AcaoModel {

	private int id_acao;
	private String nome;
	private String descricao;
	private boolean ativo;
	private List<ExecucaoModel> execucoes;

	public AcaoModel() {
	}

	public AcaoModel(int id_acao, String nome, String descricao, boolean ativo, List<ExecucaoModel> execucoes) {
		super();
		this.id_acao = id_acao;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
		this.execucoes = execucoes;
	}

	@Id
	@Column(name = "ID_ACAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_SEQ")
	@SequenceGenerator(name = "ACAO_SEQ", sequenceName = "ACAO_SEQ", allocationSize = 1)
	public int getId_acao() {
		return id_acao;
	}

	public void setId_acao(int id_acao) {
		this.id_acao = id_acao;
	}

	@Column(name = "NOME")
	@NotNull(message = "Nome é obrigatório")
	@Size(max = 40, message = "Nome deve ser menor que 40 caracteres")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "DESCRICAO")
	@NotNull(message = "Descrição é obrigatório")
	@Size(max = 100, message = "Descrição deve ser menor que 100 caracteres")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "ATIVO")
	@NotNull(message = "Ativo é obrigatório")
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "acao", cascade = CascadeType.ALL)
	public List<ExecucaoModel> getExecucoes() {
		return execucoes;
	}

	public void setExecucoes(List<ExecucaoModel> execucoes) {
		this.execucoes = execucoes;
	}
	
}
