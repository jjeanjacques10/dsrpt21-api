package br.com.fiap.business;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.exception.ReponseBusinessException;
import br.com.fiap.model.AcaoModel;
import br.com.fiap.model.ExecucaoModel;
import br.com.fiap.repository.AcaoRepository;

@SpringBootTest
public class ExecucaoBusinessTest {

	@InjectMocks
	public ExecucaoBusiness execucaoBusiness;

	@Mock
	public AcaoRepository acaoRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = ReponseBusinessException.class)
	public void testVerifyAcaoAtivoFalse() throws ReponseBusinessException {

		// GIVEN
		AcaoModel acaoModel = new AcaoModel(1, "teste", "teste", false, null);
		ExecucaoModel execucaoModel = new ExecucaoModel(1, acaoModel, "teste");
				
		// WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(acaoModel));
		execucaoBusiness.verifyAcaoAtivo(execucaoModel.getAcao());
	}
	
	@Test
	public void testVerifyAcaoAtivoTrue() throws ReponseBusinessException {

		// GIVEN
		AcaoModel acaoModel = new AcaoModel(1, "teste", "teste", true, null);
		ExecucaoModel execucaoModel = new ExecucaoModel(1, acaoModel, "teste");
				
		// WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(acaoModel));
		execucaoBusiness.verifyAcaoAtivo(execucaoModel.getAcao());
	}
	
	@Test
	public void testApplyBusiness() throws ReponseBusinessException {

		// GIVEN
		AcaoModel acaoModel = new AcaoModel(1, "teste", "teste", true, null);
		ExecucaoModel execucaoModel = new ExecucaoModel(1, acaoModel, "teste");
		
		ExecucaoModel expected = new ExecucaoModel(1, acaoModel, "teste");

		// WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(acaoModel));
		ExecucaoModel actual = execucaoBusiness.applyBusiness(execucaoModel);

		// THEN
		Mockito.verify(acaoRepository, Mockito.times(1)).findById(Mockito.anyInt());
		assertEquals("Erro", expected.toString(), actual.toString());

	}
}
