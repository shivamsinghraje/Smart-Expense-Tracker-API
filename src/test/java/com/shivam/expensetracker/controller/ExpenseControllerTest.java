package com.shivam.expensetracker.controller;


import tools.jackson.databind.ObjectMapper;
import com.shivam.expensetracker.model.Expense;
import com.shivam.expensetracker.service.ExpenseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;


@WebMvcTest(ExpenseController.class)
class ExpenseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ExpenseService expenseService;

    @Test
    void shouldCreateExpense()
            throws Exception {

        Expense expense =
                new Expense(
                        1L,
                        "Lunch",
                        200.0,
                        "Food",
                        LocalDate.now()
                );

        when(
                expenseService.addExpense(any())
        )
                .thenReturn(expense);

        mockMvc.perform(
                        post("/expenses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(expense)
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(
                        jsonPath("$.title")
                                .value("Lunch")
                );
    }

    @Test
    void shouldGetAllExpenses()
            throws Exception {
        when(
                expenseService.getAllExpenses()
        )
                .thenReturn(
                        List.of(
                                new Expense(
                                        1L,
                                        "Lunch",
                                        200.0,
                                        "Food",
                                        LocalDate.now()
                                )
                        )
                );
        mockMvc.perform(
                        get("/expenses")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.length()")
                                .value(1)
                );

    }

}