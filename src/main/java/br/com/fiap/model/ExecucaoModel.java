package br.com.fiap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "EXECUCAO")
public class ExecucaoModel {

	private int id_execucao;
	private AcaoModel acao;
	private String data_execucao;

	public ExecucaoModel() {
		super();
	}

	public ExecucaoModel(int id_execucao, AcaoModel acao, String data_execucao) {
		super();
		this.id_execucao = id_execucao;
		this.acao = acao;
		this.data_execucao = data_execucao;
	}

	@Id
	@Column(name = "ID_EXECUCAO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUCAO_SEQ")
	@SequenceGenerator(name = "EXECUCAO_SEQ", sequenceName = "EXECUCAO_SEQ", allocationSize = 1)
	public int getId_execucao() {
		return id_execucao;
	}

	public void setId_execucao(int id_execucao) {
		this.id_execucao = id_execucao;
	}

	@ManyToOne()
	@JoinColumn(name = "ID_ACAO", nullable = false)
	public AcaoModel getAcao() {
		return acao;
	}

	public void setAcao(AcaoModel acao) {
		this.acao = acao;
	}

	@Column(name = "DATA_EXECUCAO")
	@NotNull(message = "Data de execução é obrigatória")
	public String getData_execucao() {
		return data_execucao;
	}

	public void setData_execucao(String data_execucao) {
		this.data_execucao = data_execucao;
	}

	@Override
	public String toString() {
		return "ExecucaoModel [id_execucao=" + id_execucao + ", acao=" + acao + ", data_execucao=" + data_execucao
				+ "]";
	}
}