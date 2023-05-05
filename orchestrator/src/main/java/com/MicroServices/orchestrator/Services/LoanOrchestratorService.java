package com.MicroServices.orchestrator.Services;
import com.MicroServices.orchestrator.models.FormModel;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static java.lang.Long.parseLong;

@Service
public class LoanOrchestratorService {
    // steps is the list of sorted steps a loan Requests goes through
    private List<Function<String[],Boolean>> steps;

    LoanOrchestratorService(){
        this.steps= new ArrayList();
        // first step : we validate the input that the client provided
        steps.add(args -> validateClientRequest(args[0],args[1],args[2]));
        // second step : we compute the score
        steps.add(args -> commercialScoring(args[0],args[1],args[2]));
    }

    public boolean validateClientRequest(String bankId, String name, String amount){
        // we call form Valdiation Micro Service .
        RestTemplate restTemplate = new RestTemplate();
        String uri= "http://localhost:8082/Validator";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject loanRequest = new JSONObject().
                put("clientName", name).
                put("amount", amount);

        HttpEntity<String> request =
                new HttpEntity<String>(loanRequest.toString(), headers);
        try{
            ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
            return !result.getStatusCode().isError();
            }
        catch( Exception ex){
            System.out.println(ex.toString());
            return false;
        }
    }

    public boolean commercialScoring(String bankId, String name, String amount){
        // we call commercial scoring Micro Service .
        System.out.println("calculating the score in progress");
        //to do
        return parseLong(amount) < 500;
    }

    public ResponseEntity<?> startProcessFromClientInput(String bankId, String name, String amount) {
        // let form submission ms manage the form model
        String[] args= { bankId, name,amount };
        // we iterate over & apply the steps .
        for (Function<String[],Boolean> step: this.steps) {
            boolean isSucess= step.apply(args);
            if (!isSucess){
                return new ResponseEntity<String>("sorry , we can't give you the loan, "+name, HttpStatus.OK);
            }
        }
            return new ResponseEntity<String>("congrats "+ name+" , we will give you the loan", HttpStatus.OK);
        }
}
