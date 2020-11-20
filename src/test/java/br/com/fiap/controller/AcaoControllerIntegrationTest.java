package br.com.fiap.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class AcaoControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	@DisplayName("Deve listar todas ações e retornar status 200")
	public void shouldListAllAcoes() throws Exception {

		mvc.perform(get("/acao").contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Deve salvar uma ação e retornar com status 200")
	public void shouldSaveAcao() throws Exception {
		mvc.perform(post("/acao").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\": \"Ação de Teste\", \"descricao\": \"Esse é um teste\", \"ativo\": false}"))
				.andDo(print()).andExpect(status().isCreated()).andExpect(header().exists("Location"));
	}

	@Test
	@DisplayName("Deve retornar uma ação pelo ID e com status 200")
	public void shouldFindAcaoById() throws Exception{
		mvc.perform(get("/acao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"id_acao\":1,\"nome\":\"Ativar Armas\",\"descricao\":\"Ativa as armas\",\"ativo\":true}"));
	}
	
	@Test
	@DisplayName("Deve atualizar uma ação pelo ID, retornar status 200")
	public void shouldUpdateAcao() throws Exception{
		mvc.perform(put("/acao/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"nome\": \"Ação Atualizada\", \"descricao\": \"Atualização\", \"ativo\": false }"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma ação pelo ID, retornar status 204")
	public void shouldDeleteAcaoById() throws Exception{
		
		mvc.perform(delete("/acao/3")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
}
