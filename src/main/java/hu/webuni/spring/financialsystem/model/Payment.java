package hu.webuni.spring.financialsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Payment {
    long studentId;
    int cashPaidIn;
}
