package com.ikea.fuzzy.dtos;

public record Product(Long id, String name, String category, String description, long price, String imageUrl) {
}
