package hescha.expectedapplication.model;

import lombok.Data;

import javax.persistence.Id;

@Data
public class AbstractEntity {
    @Id
    private Long id;
}
