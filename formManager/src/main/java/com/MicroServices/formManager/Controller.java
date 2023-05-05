package com.MicroServices.formManager;

import com.MicroServices.formManager.Services.FormManagementService;
import com.MicroServices.formManager.models.FormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private FormManagementService service;

    @GetMapping("/")
    public ResponseEntity<String> testMicroService(){
        System.out.println("Form Management micro Service works!");
        return new ResponseEntity<String>("Form Management micro Service works!", HttpStatus.OK);
    }

    @PostMapping(value = "/Validator", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validate(@RequestBody FormModel formInput){
        System.out.println("this input is valide" + formInput.clientName);
        return  new ResponseEntity<String>(formInput.clientName, HttpStatus.OK);
    }
}
