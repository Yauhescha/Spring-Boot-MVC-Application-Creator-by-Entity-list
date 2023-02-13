package main.java.hescha.expectedapplication.model;

import lombok.Data;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Entity
@Table
@Data
public class Category {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private String title;
    private BigDecimal price;
    private Boolean onboard;
    private int contributionNumber;
    private double newPrice;
    private LocalDate bornDate;
    private LocalDate birthDate;
    private Set<Role> roles = new HashSet<>();
    private List<String> jobs = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();
}
