package br.com.autosoft.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tb_order_labor")
public class OrderLabor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected Integer quantity;
    
    @Column(name = "sub_total")
    protected Double subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "labor_id")
    private Labor labor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    protected Order order;

    public double getSubTotal() {
        return this.quantity * labor.getPrice();
    }

    public OrderLabor(Integer id, Integer quantity, Double price, Labor labor) {
        this.id = id;
        this.quantity = quantity;
        this.labor = labor;
        this.subTotal = getSubTotal();
    }
}
