package br.com.daniloewerton.apiv1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@Entity(name = "ProductEntity")
public class Product {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "category")
    private String category;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
