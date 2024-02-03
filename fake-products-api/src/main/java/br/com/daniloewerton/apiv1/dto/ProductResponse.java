package br.com.daniloewerton.apiv1.dto;


import java.math.BigDecimal;

public record ProductResponse(String id, String title, BigDecimal price, String category, String description, String image) {

}
