package com.shivam.expensetracker.service;

import com.shivam.expensetracker.exception.ResourceNotFoundException;
import com.shivam.expensetracker.model.Expense;
import com.shivam.expensetracker.repository.ExpenseRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ExpenseServiceTest {

    private ExpenseService expenseService;


    @BeforeEach
    void setUp() {
        ExpenseRepository repository = new ExpenseRepository();
        expenseService = new ExpenseService(repository);
    }


    private Expense createExpense(
            String title,
            double amount,
            String category) {

        Expense expense = new Expense();

        expense.setTitle(title);
        expense.setAmount(amount);
        expense.setCategory(category);
        expense.setDate(LocalDate.now());
        return expense;
    }



    @Test
    void shouldAddExpense() {
        Expense expense =
                createExpense(
                        "Lunch",
                        200,
                        "Food"
                );

        Expense savedExpense =
                expenseService.addExpense(expense);

        assertNotNull(savedExpense);
        assertNotNull(savedExpense.getId());
        assertEquals(
                "Lunch",
                savedExpense.getTitle()
        );
    }



    @Test
    void shouldReturnAllExpenses() {
        expenseService.addExpense(
                createExpense(
                        "Lunch",
                        200,
                        "Food"
                )
        );


        List<Expense> expenses =
                expenseService.getAllExpenses();
        assertEquals(
                1,
                expenses.size()
        );

    }




    @Test
    void shouldFilterExpenseByCategory() {
        expenseService.addExpense(
                createExpense(
                        "Lunch",
                        200,
                        "Food"
                )
        );
        expenseService.addExpense(
                createExpense(
                        "Bus",
                        50,
                        "Transport"
                )
        );


        List<Expense> result =
                expenseService.getExpensesByCategory("Food");
        assertEquals(
                1,
                result.size()
        );


        assertEquals(
                "Lunch",
                result.get(0).getTitle()
        );
    }





    @Test
    void shouldCalculateTotalExpense() {
        expenseService.addExpense(
                createExpense(
                        "Lunch",
                        200,
                        "Food"
                )
        );


        expenseService.addExpense(
                createExpense(
                        "Bus",
                        50,
                        "Transport"
                )
        );


        double total =
                expenseService.calculateTotalExpenses();
        assertEquals(
                250,
                total
        );
    }





    @Test
    void shouldCalculateTotalByCategory() {
        expenseService.addExpense(
                createExpense(
                        "Lunch",
                        200,
                        "Food"
                )
        );
        expenseService.addExpense(
                createExpense(
                        "Dinner",
                        300,
                        "Food"
                )
        );


        expenseService.addExpense(
                createExpense(
                        "Bus",
                        50,
                        "Transport"
                )
        );


        double total =
                expenseService.calculateTotalByCategory("Food");
        assertEquals(
                500,
                total
        );
    }





    @Test
    void shouldDeleteExpense() {
        Expense expense =
                expenseService.addExpense(
                        createExpense(
                                "Lunch",
                                200,
                                "Food"
                        )
                );

        expenseService.deleteExpense(
                expense.getId()
        );
        List<Expense> expenses =
                expenseService.getAllExpenses();

        assertTrue(
                expenses.isEmpty()
        );
    }





    @Test
    void shouldThrowExceptionWhenExpenseNotFound() {
        assertThrows(
                ResourceNotFoundException.class,
                () ->
                        expenseService.deleteExpense(100L)
        );
    }


}