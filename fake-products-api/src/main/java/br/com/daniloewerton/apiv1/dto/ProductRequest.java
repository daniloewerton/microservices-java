package br.com.daniloewerton.apiv1.dto;


import java.math.BigDecimal;

public record ProductRequest(String id, String title, BigDecimal price, String category, String description, String image) {

}
