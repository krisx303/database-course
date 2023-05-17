package com.group.travels.api.payments;

import java.util.List;

public record PaymentRequest (List<Long> bookingIDs){
}
