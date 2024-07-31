package com.expensetracker.expensetracker.controller;

import com.expensetracker.expensetracker.model.Expense;
import com.expensetracker.expensetracker.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public Map<String, String> Home() {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "Hello world");
        return map;
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> expenses = expenseService.getAllExpenses();
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getSingleExpense(@PathVariable UUID id) {
        Optional<Expense> expense = expenseService.getSingleExpense(id);
        return expense.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-expense")
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        Expense newExpense = expenseService.saveExpense(expense);
        return ResponseEntity.ok(newExpense);
    }

    @PutMapping("/update-expense/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable UUID id, @RequestBody Expense expense) {
        if (!expenseService.getSingleExpense(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        expense.setId(id);
        Expense updatedExpense = expenseService.saveExpense(expense);
        return ResponseEntity.ok(updatedExpense);
    }

    @DeleteMapping("/delete-expense/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable UUID id) {
        if (!expenseService.getSingleExpense(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

}

