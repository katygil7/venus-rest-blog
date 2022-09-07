package kat.venusrestblog;

import org.springframework.web.bind.annotation.PathVariable;

public class FakeDogFactGateway {
    public static String fetchDogFactById(@PathVariable long id){
//    fetch the particular dog fact with matching id from database
        switch ((int) id){
            case 1:
                return "Dog don't feel guilty";
            case 2:
                return "Their sense of smell is really powerful";
            default:
                return "Unknown fact id";
        }
    }
}
