package com.guest.guestbookservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guest.guestbookservice.models.Visitor;
import com.guest.guestbookservice.repositories.GuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuestRepository guestRepository;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        guestRepository.deleteAll();
    }

    @Test
    public void addVisitor() throws Exception {
        Visitor visitor = new Visitor("Eric","Very good");

        mockMvc.perform(post("/guest/visitor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(visitor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Eric"))
                .andExpect(jsonPath("$.comment").value("Very good"));
    }

    @Test
    public void getAllVisitors() throws Exception {

        Visitor visitor1 = new Visitor("Eric","Very good");
        Visitor visitor2 = new Visitor("Nityam","Awesome");
        Visitor visitor3 = new Visitor("Sam","Great");
        List<Visitor> expectedVisitorList = new ArrayList<>();
        expectedVisitorList.add(visitor1);
        expectedVisitorList.add(visitor2);
        expectedVisitorList.add(visitor3);

        guestRepository.save(visitor1);
        guestRepository.save(visitor2);
        guestRepository.save(visitor3);

        String actualVisitorsList = mockMvc.perform(get("/guest/visitors"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(expectedVisitorList);

        assertThat(expected).isEqualTo(actualVisitorsList);

    }

}
