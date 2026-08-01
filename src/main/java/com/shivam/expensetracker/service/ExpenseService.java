package com.shivam.expensetracker.service;

import com.shivam.expensetracker.exception.ResourceNotFoundException;
import com.shivam.expensetracker.model.Expense;
import com.shivam.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {
    private long nextId=1L;


    private final ExpenseRepository repository;
    public ExpenseService(ExpenseRepository repository) {
        this.repository = repository;
    }


    //add expense
    public Expense addExpense(Expense expense) {
        expense.setId(nextId++);
        return repository.save(expense);
    }


    //Get all expenses
    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }


    //Filter by category
    public List<Expense> getExpensesByCategory(String category) {
        List<Expense> result = new ArrayList<>();
        for (Expense e : repository.findAll()) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                result.add(e);
            }
        }
        return result;
    }


    //Calculates total
    public Double calculateTotalExpenses() {
        double total = 0;
        for (Expense e : repository.findAll()) {
            total += e.getAmount();
        }
        return total;
    }


    //Calculate total by category
    public Double calculateTotalByCategory(String category) {
        double total = 0;
        for (Expense e : repository.findAll()) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                total += e.getAmount();
            }
        }
        return total;
    }



    // delete expense by id
    public void deleteExpense(Long id) {
        boolean deleted = repository.deleteById(id);
        if (!deleted) {
            throw new ResourceNotFoundException("Expense not found with id : " + id);
        }
    }


}
