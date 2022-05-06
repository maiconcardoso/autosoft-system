package br.com.autosoft.dtos;

import br.com.autosoft.entities.Labor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LaborDTO {

    private Integer id;
    private String description;
    private String groupFamily;
    private String subGroup;
    private String application;
    private Double price;

    public LaborDTO(Labor labor) {
        this.id = labor.getId();
        this.description = labor.getDescription();
        this.groupFamily = labor.getGroupFamily();
        this.subGroup = labor.getSubGroup();
        this.application = labor.getApplication();
        this.price = labor.getPrice();
    }
}
