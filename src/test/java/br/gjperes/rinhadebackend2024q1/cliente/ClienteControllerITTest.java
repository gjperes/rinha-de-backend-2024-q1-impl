package br.gjperes.rinhadebackend2024q1.cliente;

import br.gjperes.rinhadebackend2024q1.config.AbstractIntegrationTest;
import br.gjperes.rinhadebackend2024q1.transacao.TransacaoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClienteControllerITTest extends AbstractIntegrationTest {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        transacaoRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        clienteRepository.deleteAll();
        transacaoRepository.deleteAll();
    }

    @Test
    void testCoisa() throws Exception {
        mockMvc.perform(get("/clientes/1/extrato")).andDo(print()).andExpect(status().isOk());
    }
}