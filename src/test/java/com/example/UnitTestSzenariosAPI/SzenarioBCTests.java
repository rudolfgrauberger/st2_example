package com.example.UnitTestSzenariosAPI;

import com.example.controller.BestellungController;
import com.example.controller.GerichtController;
import com.example.controller.SpeisekarteController;
import com.example.entities.Speisekarte;
import com.example.service.BestellungService;
import com.example.service.GerichtService;
import com.example.service.SpeisekarteService;
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
@WebMvcTest({GerichtController.class, SpeisekarteController.class})
public class SzenarioBCTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerichtService gerichtService;
    @MockBean
    private SpeisekarteService speisekarteService;

    @InjectMocks
    private GerichtController gerichtController;
    @InjectMocks
    private SpeisekarteController speisekarteController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGericht() throws Exception {
        mockMvc.perform(get("/gerichte")).andExpect(status().is(200)).andReturn();
    }

    @Test
    public void testGetSpeisekarte() throws Exception {
        mockMvc.perform(get("/speisekarten")).andExpect(status().is(200)).andReturn();
    }
}
