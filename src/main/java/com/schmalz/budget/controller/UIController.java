package com.schmalz.budget.controller;


import com.schmalz.budget.model.Expense;
import com.schmalz.budget.model.ExpenseManaging;
import com.schmalz.budget.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;


@Controller
public class UIController {

    @Autowired
    private ExpenseRepository repo;

    private ExpenseManaging daten = new ExpenseManaging();



    //eintrag abschicken
    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute Expense expense){
        repo.save(expense);
        return "redirect:/";
    }
    @PostMapping("/submitModifiedForm")
    public String submitModifiedForm(@ModelAttribute Expense expense){
        repo.save(expense);
        return "redirect:/";
    }

    //neuer eintrag
    @GetMapping("/expenseForm")
    public String expenseForm(Model model){
        model.addAttribute("expense",new Expense());
        return "inputForm";
    }
    //eintrag löschen
    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam(value ="id")Long id){
        repo.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/modifyExpense")
    public String modifyExpense(@RequestParam(value ="id")Long id, Model model){
        model.addAttribute("expense",repo.findById(id));
        repo.deleteById(id);
        return "modifyForm";
    }



    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(value = "filter", required     = false) Integer filter,
                        @RequestParam(value = "param1", required     = false) String param1,
                        @RequestParam(value = "param2", required     = false) String param2,
                        @RequestParam(value = "sortBy", required     = false) String sortBy,
                        @RequestParam(value = "increasing", required = false) Boolean increasing) {
        ArrayList<Expense> expenseList= (ArrayList<Expense>) repo.findAll();
        daten.sortByDate(expenseList,true);
        if(filter!=null){
            if(param1!=null){
                expenseList = filter(expenseList,filter,param1,param2);
                model.addAttribute("param1",param1);
            }
            model.addAttribute("filter",filter);
            if (param2!=null)
                model.addAttribute("param2",param2);
        }
        if(sortBy!=null){
            sortBy(expenseList,sortBy,increasing);
        }
        model.addAttribute("bl",expenseList);

        return "index";
    }

    private ArrayList<Expense> filter(ArrayList<Expense> list,int filter, String param1, String param2){
        switch (filter){
            case 2:
                return (ArrayList<Expense>) daten.getForDateRange(list,param1,param2);
            case 3:
                return (ArrayList<Expense>) daten.getWithName(list,param1);
            case 4:
                return (ArrayList<Expense>) daten.getForAmountRange(list,Integer.parseInt(param1),Integer.parseInt(param2));
            case 5:
                return (ArrayList<Expense>) daten.getWithKeyword(list,param1);
            default:
                return list;

        }
    }

    private void sortBy(ArrayList<Expense> list, String by, boolean incr){
        switch (by){
            case "date":
                daten.sortByDate(list, incr);
                return;
            case "name":
                daten.sortByName(list, incr);
                return;
            case "amount":
                daten.sortByAmount(list, incr);
                return;
            default:
        }
    }

}
