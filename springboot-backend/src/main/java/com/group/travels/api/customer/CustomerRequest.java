package com.group.travels.api.customer;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(@NotBlank String customerName,
                              @NotBlank String customerEmail,
                              @NotBlank String customerPhone) {
}
