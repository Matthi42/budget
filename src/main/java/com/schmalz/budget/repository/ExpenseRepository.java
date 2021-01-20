package com.schmalz.budget.repository;

import com.schmalz.budget.model.Expense;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense,Long> {
    List<Expense> findAll();
}
