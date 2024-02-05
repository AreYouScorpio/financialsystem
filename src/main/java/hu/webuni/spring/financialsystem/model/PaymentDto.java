package hu.webuni.spring.financialsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDto {
    long studentId;
    int cashPaidIn;
}
