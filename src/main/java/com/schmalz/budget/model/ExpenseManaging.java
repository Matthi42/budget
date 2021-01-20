package com.schmalz.budget.model;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ExpenseManaging {
    public List<Expense> getForDateRange(List<Expense>all ,String start, String end){
        LocalDate date1=LocalDate.parse(start);
        LocalDate date2=LocalDate.parse(end);
        List<Expense> retur=new ArrayList<>();
        for (Expense e:
                all) {
            if((e.getDate().isBefore(date2)||e.getDate().isEqual(date2))
                    &&((e.getDate().isAfter(date1))||(e.getDate().isEqual(date1)))){
                retur.add(e);
            }
        }
        return retur;
    }

    public List<Expense> getWithName(List<Expense>all , String name){
        List<Expense> retur=new ArrayList<>();
        for (Expense e :
                all) {
            if (name.equalsIgnoreCase(e.getName())) {
                retur.add(e);
            }
        }
        return retur;
    }

    public List<Expense> getForAmountRange(List<Expense>all ,int lowerLimit, int upperLimit) {
        List<Expense> retur=new ArrayList<>();
        for (Expense e :
                all) {
            if (e.getBetrag() > lowerLimit && e.getBetrag() < upperLimit) {
                retur.add(e);
            }
        }
        return retur;
    }

    public List<Expense> getWithKeyword(List<Expense>all, String keyword){
        List<Expense> retur=new ArrayList<>();
        for (Expense e :
                all) {
            if (e.getNotiz().toLowerCase().contains(keyword.toLowerCase()) ||
                    e.getName().toLowerCase().contains(keyword.toLowerCase()))
                retur.add(e);
        }
        return retur;
    }


    public void sortByDate(List<Expense> list, boolean incr){
        Comparator<Expense> comp=Comparator.comparing(Expense::getDate);
        if(!incr)
            list.sort(comp);
        else
            list.sort(Collections.reverseOrder(comp));
    }

    public void sortByName(List<Expense> list, boolean incr){
        Comparator<Expense> comp=Comparator.comparing(Expense::getName);
        if(!incr)
            list.sort(comp);
        else
            list.sort(Collections.reverseOrder(comp));
    }

    public void sortByAmount(List<Expense> list, boolean incr){
        Comparator<Expense> comp=Comparator.comparing(Expense::getBetrag);
        if(!incr)
            list.sort(comp);
        else
            list.sort(Collections.reverseOrder(comp));
    }

}
