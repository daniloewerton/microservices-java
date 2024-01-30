package br.com.daniloewerton.apiv1.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String title;
    private BigDecimal price;
    private String category;
    private String description;
    private String image;
}
