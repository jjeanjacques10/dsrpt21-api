package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ExecucaoControllerIntegrationTest {
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas as execuções e retornar status 200")
	public void shouldListAllExecucoes() throws Exception {

		mvc.perform(get("/execucao").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve salvar uma execução e retornar com status 200")
	public void shouldSaveExecucao() throws Exception {
		mvc.perform(post("/execucao").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"acao\": { \"id_acao\": 1 }, \"data_execucao\": \"2020-05-01 12:30:00\" }"))
				.andDo(print()).andExpect(status().isCreated()).andExpect(header().exists("Location"));
	}
	
	@Test
	@DisplayName("Deve retornar uma execução pelo ID e com status 200")
	public void shouldFindExecucaoById() throws Exception{
		mvc.perform(get("/execucao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"id_execucao\":1,\"acao\":{\"id_acao\":1,\"nome\":\"Ativar Armas\",\"descricao\":\"Ativa as armas\",\"ativo\":true},\"data_execucao\":\"2020-05-01 12:30:00\"}"));
	}
	
	@Test
	@DisplayName("Deve atualizar uma execução pelo ID, retornar status 200")
	public void shouldUpdateAcao() throws Exception{
		mvc.perform(put("/execucao/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"acao\": { \"id_acao\": 2 }, \"data_execucao\": \"2020-05-01 12:30:00\" }"))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
