package com.example.UnitTestAPI;

import com.example.controller.BestellungController;
import com.example.service.BestellungService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BestellungController.class)
public class BestellungControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BestellungService bestellungService;

    @InjectMocks
    private BestellungController bestellungController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostBestellung() throws Exception {
        mockMvc.perform(post("/bestellungen")).andExpect(status().is(200)).andReturn();
    }

    @Test
    public void testGetBestellungO1010() throws Exception {
        mockMvc.perform(get("/bestellungen/O1010")).andExpect(status().is(200)).andReturn();
    }

}
