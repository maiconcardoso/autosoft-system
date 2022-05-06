package br.com.autosoft.dtos;


import br.com.autosoft.entities.Labor;
import br.com.autosoft.entities.OrderLabor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderLaborDTO {
    
    private Integer id;
    private Integer quantity;
    private Labor labor;
    private Double subTotal;

    public OrderLaborDTO(OrderLabor orderLabor) {
        this.id = orderLabor.getId();
        this.quantity = orderLabor.getQuantity();
        this.labor = orderLabor.getLabor();
        this.subTotal = orderLabor.getSubTotal();
    }    
}
