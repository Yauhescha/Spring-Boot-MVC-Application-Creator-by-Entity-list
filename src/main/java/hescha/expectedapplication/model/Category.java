package hescha.expectedapplication.model;

import lombok.Data;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table
@Entity
@Data
public class Category extends AbstractEntity {
    private String name;
    @ElementCollection
    private Set<Role> roles = new HashSet<>();
    @ElementCollection
    private List<String> categories = new ArrayList<>();
}
