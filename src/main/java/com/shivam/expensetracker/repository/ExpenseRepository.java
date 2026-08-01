package com.shivam.expensetracker.repository;

import com.shivam.expensetracker.model.Expense;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ExpenseRepository {

    private final List<Expense> expenses = new ArrayList<>();

    //Saving new expense
    public Expense save(Expense expense) {
        expenses.add(expense);
        return expense;
    }

    //Returning all Expenses
    public List<Expense> findAll(){
        return expenses;
    }

    //Find expense by Id
    public Expense findById(Long id){
        for (Expense e : expenses){
            if (e.getId().equals(id)){
                return e;
            }
        }
        return null;
    }

    //delete by Id
    public boolean deleteById(Long id) {
        Expense expense = findById(id);
        if (expense != null) {
            expenses.remove(expense);
            return true;
        }
        return false;
    }

}
