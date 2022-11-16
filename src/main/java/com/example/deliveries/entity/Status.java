package com.example.deliveries.entity;

import lombok.Getter;

@Getter
public enum Status {
    CREATED("Создано"),
    APPROVED("Подтверждено"),
    SHIPPING("Отправлено"),
    DELIVERED("Получено");

    public final String descriptionRu;

    Status(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }
}
