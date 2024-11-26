package org.example.dto;

import java.time.LocalDate;

public class PaymentDto {
    private String id;
    private String eid;
    private String amount;
    private LocalDate date;

    public PaymentDto() {
    }

    public PaymentDto(String id, String eid, String amount, LocalDate date) {
        this.id = id;
        this.eid = eid;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", eid='" + eid + '\'' +
                ", amount='" + amount + '\'' +
                ", date=" + date +
                '}';
    }
}