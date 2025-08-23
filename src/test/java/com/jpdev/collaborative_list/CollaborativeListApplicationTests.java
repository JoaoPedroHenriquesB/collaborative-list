package com.jpdev.collaborative_list;

import com.jpdev.collaborative_list.controller.RoomController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CollaborativeListApplicationTests {

	@Autowired
	private RoomController roomController;

	@Test
	void contextLoads() {
		// Este teste verifica se o contexto da aplicação Spring carrega sem erros.
		// Uma verificação adicional é garantir que um de nossos controllers foi carregado.
		assertThat(roomController).isNotNull();
	}

}
