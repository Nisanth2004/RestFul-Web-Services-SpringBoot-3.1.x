package com.nisanth.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstPrson()
    {
        return new PersonV1("Nisanth Selvaraj");
    }


    @GetMapping("/v2/person")
    public PersonV2 getSecondPerson()
    {
        return new PersonV2(new Name("Nisanth","Selvaraj"));
    }
}
