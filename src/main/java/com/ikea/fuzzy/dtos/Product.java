package com.ikea.fuzzy.dtos;

public record Product(long id, String name, String category, String description, long price, String imageUrl) {
}
