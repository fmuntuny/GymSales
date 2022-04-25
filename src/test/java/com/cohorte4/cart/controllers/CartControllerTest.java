package com.cohorte4.cart.controllers;

import com.cohorte4.cart.entities.CartEntity;
import com.cohorte4.cart.repositories.CartRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartRepository cartRepository;

    ObjectMapper mapper =
            new ObjectMapper().registerModule(new JavaTimeModule())
                    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public ObjectWriter mapperConfig(){
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow;
    }

    @Test
    void getAll() throws Exception {
        when(cartRepository.findAll()).thenReturn(singletonList(buildCartEntity()));
        mockMvc.perform(get("/api/carts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapperConfig().writeValueAsString(singletonList(buildCartEntity()))));
    }

    @Test
    void getById() throws Exception {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(buildCartEntity()));
        mockMvc.perform(get("/api/carts/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(mapperConfig().writeValueAsString(buildCartEntity())));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/api/carts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapperConfig().writeValueAsString(buildCartEntity())))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void update() throws Exception {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(buildCartEntity()));
        mockMvc.perform(put("/api/carts/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapperConfig().writeValueAsString(buildCartEntity())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteCart() throws Exception{
        when(cartRepository.findById(1L)).thenReturn(Optional.of(buildCartEntity()));
        mockMvc.perform(delete("/api/carts/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    public CartEntity buildCartEntity(){
        CartEntity cartEntity = new CartEntity();
        cartEntity.setId(1L);
        cartEntity.setTotalPrice(0.0);
        cartEntity.setItemsNumber(0);
        cartEntity.setItemsList(new ArrayList<>());
        cartEntity.setMeansOfPayment("MeansOfPayment 1");
        return cartEntity;
    }
}