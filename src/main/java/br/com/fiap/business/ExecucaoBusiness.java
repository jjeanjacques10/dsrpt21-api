package br.com.fiap.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.exception.ReponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;

@Component
public class ExecucaoBusiness {

	@Autowired
	private AcaoRepository acaoRepository;

	public ExecucaoModel applyBusiness(ExecucaoModel execucaoModel) throws ReponseBusinessException {

		verifyAcaoAtivo(execucaoModel.getAcao());

		return execucaoModel;
	}

	protected void verifyAcaoAtivo(AcaoModel acaoModel) throws ReponseBusinessException {
		acaoModel = acaoRepository.findById(acaoModel.getId_acao()).get();
		if (!acaoModel.isAtivo()) {
			throw new ReponseBusinessException("A ação precisa estar ativa");
		}
	}
}
