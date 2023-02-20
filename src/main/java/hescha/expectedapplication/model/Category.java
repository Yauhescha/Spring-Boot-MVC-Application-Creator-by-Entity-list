package hescha.expectedapplication.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table
@Entity
@Data
public class Category extends AbstractEntity {
    private String name;
    private Double addingPrice;
}
