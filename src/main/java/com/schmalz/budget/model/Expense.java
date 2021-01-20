package com.schmalz.budget.model;

import javax.persistence.Id;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity(name="Expense")
public class Expense {

    @Id
    @GeneratedValue
    private long id;
    private int betrag;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private String notiz;



    public Expense(int betrag, String name, LocalDate date, String notiz){
        this.betrag=betrag;
        this.date=date;
        this.name=name;
        this.notiz=notiz;
    }

    public Expense(){}

    public int getBetrag() {
        return betrag;
    }

    public String getBetragInEuro(){
        String cent=String.valueOf(betrag);
        if (cent.length()>2)
            return betrag/100 + ","+ cent.subSequence(cent.length()-2,cent.length());
        else
        if(cent.length()<2)
            return "0,0"+betrag;
        else
            return "0,"+ betrag;
    }

    public void setBetrag(int betrag) {
        this.betrag = betrag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotiz() {
        return notiz;
    }

    public void setNotiz(String notiz) {
        this.notiz = notiz;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object t){
        try{
            Expense a=(Expense) t;
            return a.getBetrag() == this.getBetrag()
                    && a.getDate().equals(this.getDate())
                    && a.getName().equals(this.getName())
                    && a.getNotiz().equals(this.getNotiz());
        }catch(ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString(){
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+"   "+name+"   "+getBetragInEuro()+"€     "+ notiz;
    }

}
