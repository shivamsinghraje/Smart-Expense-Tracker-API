package com.shivam.expensetracker.controller;

import com.shivam.expensetracker.model.Expense;
import com.shivam.expensetracker.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    //Add expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(
            @RequestBody Expense expense) {
        Expense savedExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(
                savedExpense,
                HttpStatus.CREATED);
    }


    //Get all expenses
    @Operation(
            summary = "Get all expenses",
            description = "Returns a list of all expenses")
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(){
        return ResponseEntity.ok(
                expenseService.getAllExpenses());
    }


    //Filter by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getByCategory(
            @PathVariable String category){
        return ResponseEntity.ok(
                expenseService.getExpensesByCategory(category));
    }


    //Calculate total expense
    @GetMapping("/total")
    public ResponseEntity<Double> getTotal(){
        return ResponseEntity.ok(
                expenseService.calculateTotalExpenses());
    }


    //Calculate total by category
    @GetMapping("/total/{category}")
    public ResponseEntity<Double> getTotalByCategory(
            @PathVariable String category){

        return ResponseEntity.ok(
                expenseService.calculateTotalByCategory(category));

    }


    //Delete expense
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(
            @PathVariable Long id){
        expenseService.deleteExpense(id);

        return ResponseEntity.ok("Expense deleted successfully");

    }

}
