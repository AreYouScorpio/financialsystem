package hu.webuni.spring.financialsystem.web;

import hu.webuni.spring.financialsystem.model.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/financialsystem")
public class FinancialSystemController {




        private final JmsTemplate jmsTemplate;

        @PostMapping("/{studentId}/{paidInSum}")
        //public Payment postPaidInSum(@PathVariable long studentId, @PathVariable int paidInSum) {
        public PaymentDto postPaidInSum(@PathVariable long studentId, @PathVariable int paidInSum) {


            System.out.println("postPaidInSum within FinancialSystem called");
            System.out.println("Paid in sum in HUF: " + paidInSum + " Ft");



            PaymentDto payload = new PaymentDto(studentId,paidInSum);
            System.out.println("Payment payload: " + payload);

            jmsTemplate.convertAndSend("payments", payload); // a queue vagy topic neve // es amit kuldunk // uaz a studentben a jms/PaymentMessageConsumer-ben a @JmsListenerben

            return payload;

        }

    }



