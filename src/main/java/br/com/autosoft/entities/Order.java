package br.com.autosoft.entities;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.CreatedDate;

import br.com.autosoft.enuns.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tb_order")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    private OrderStatus status;

    @OneToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;   
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderItem> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderLabor> labors;

    private Double amount;

    public double getTotal() {
        double totalItems = 0.0;
        double totalLabors = 0.0;
        amount = 0.0;
        for (OrderItem item : items) {  totalItems += item.getSubTotal(); }
        for (OrderLabor labor : labors ) { totalLabors += labor.getSubTotal(); }
        amount = totalItems + totalLabors;
        return amount;
    }

    public OrderStatus getStatus() {
        LocalDateTime today = LocalDateTime.now();
        Duration duration = Duration.between(creationDate, today);
        long days = duration.toDays();
        if (this.status == OrderStatus.AGUARDANDO && days > 30) {
            return OrderStatus.ATRASADA;
        } else {
            return this.status;
        }
    }
}
