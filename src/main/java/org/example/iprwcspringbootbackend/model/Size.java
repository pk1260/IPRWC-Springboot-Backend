package org.example.iprwcspringbootbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private int size;
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    @JsonBackReference
    private Product product;
}
