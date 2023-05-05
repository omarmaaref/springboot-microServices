package com.MicroServices.orchestrator;
import com.MicroServices.orchestrator.Services.LoanOrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    public class Controller {

        @Autowired
        private LoanOrchestratorService service;

        @GetMapping("/")
        public ResponseEntity<?> testMS(){
            System.out.println("Loan Orchestrator microService works fine");
            return new ResponseEntity<String>("Loan Orchestrator microService works fine", HttpStatus.OK);
        }

        @GetMapping("/start")
        public ResponseEntity<?> startLoanProcessFromClientInput(@RequestParam("bankId") String bankId, @RequestParam("clientName") String clientName, @RequestParam("amount") String amount){
            System.out.println(clientName+" whants a loan with this amount : "+amount );
            return service.startProcessFromClientInput(bankId, clientName, amount);
        }
    }
