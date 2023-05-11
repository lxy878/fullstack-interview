package org.sailplatform.fsbackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Person {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    String firstName;

    String lastName;

    public Person(){}
    public Person(String fn, String ln){
        this.firstName = fn;
        this.lastName = ln;
    }
    public Person(Long id, String fn, String ln){
        this.id = id;
        this.lastName = ln;
        this.firstName = fn;
    }
}
