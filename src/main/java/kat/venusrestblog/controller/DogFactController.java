package kat.venusrestblog.controller;

import kat.venusrestblog.FakeDogFactGateway;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogFactController {
    @GetMapping("/dogo")
    public String fetchDogFact(){
        return "Puppies are a pain in the butt!";
    }
    @GetMapping("/dogo/{id}")
    public String fetchSingleDogFact (@PathVariable long id){
        return FakeDogFactGateway.fetchDogFactById(id);
    }
}
