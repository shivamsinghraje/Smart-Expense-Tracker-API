package com.shivam.expensetracker.controller;


import com.shivam.expensetracker.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
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



    //Get expense by category
    @Test
    void shouldGetExpensesByCategory() throws Exception {

        List<Expense> expenses = List.of(
                new Expense(
                        1L,
                        "Lunch",
                        200.0,
                        "Food",
                        LocalDate.now()
                )
        );

        when(expenseService.getExpensesByCategory("Food"))
                .thenReturn(expenses);

        mockMvc.perform(get("/expenses/category/Food"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Lunch"))
                .andExpect(jsonPath("$[0].category").value("Food"));
    }




    //Calculate total expense
    @Test
    void shouldCalculateTotalExpenses() throws Exception {

        when(expenseService.calculateTotalExpenses())
                .thenReturn(250.0);

        mockMvc.perform(get("/expenses/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("250.0"));
    }


    //Calculate total by category
    @Test
    void shouldCalculateTotalByCategory() throws Exception {

        when(expenseService.calculateTotalByCategory("Food"))
                .thenReturn(500.0);

        mockMvc.perform(get("/expenses/total/Food"))
                .andExpect(status().isOk())
                .andExpect(content().string("500.0"));
    }


    //Delete expense
    @Test
    void shouldDeleteExpense() throws Exception {

        doNothing()
                .when(expenseService)
                .deleteExpense(1L);

        mockMvc.perform(delete("/expenses/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Expense deleted successfully"));

        verify(expenseService)
                .deleteExpense(1L);
    }


    //Global Exception
    @Test
    void shouldReturn404WhenExpenseNotFound() throws Exception {

        doThrow(new ResourceNotFoundException("Expense not found"))
                .when(expenseService)
                .deleteExpense(100L);

        mockMvc.perform(delete("/expenses/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Expense not found"));
    }

}