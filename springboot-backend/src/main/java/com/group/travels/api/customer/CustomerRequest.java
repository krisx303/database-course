package com.group.travels.api.customer;

public record CustomerRequest(String customerName,
                              String customerEmail,
                              String customerPhone) {
}
