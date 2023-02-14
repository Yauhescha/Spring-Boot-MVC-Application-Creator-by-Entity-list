package hescha.expectedapplication.model;

import lombok.Data;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Data
public class Category {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private Set<Role> roles = new HashSet<>();
    private List<Category> categories = new ArrayList<>();

}
