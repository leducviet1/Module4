package com.example.casestudy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Không được để trống tên the loai")
    private String name;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Product> products ;
}
