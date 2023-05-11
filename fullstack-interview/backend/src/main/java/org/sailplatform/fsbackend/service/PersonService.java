package org.sailplatform.fsbackend.service;

import java.util.List;
import java.util.Optional;

import org.sailplatform.fsbackend.model.Person;
import org.sailplatform.fsbackend.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    
    public Person add(Person toAdd){
        
        return personRepository.save(toAdd);
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Person getOne(Long uid){
        return personRepository.findById(uid).orElse(null);
    }
    
    // 7. Delete a person's data
    public String delete(Long uid){
        Optional<Person> op = personRepository.findById(uid);
        if(!op.isPresent()){
            return "No found";
        }
        personRepository.delete(op.get());
        return "Person Id: "+uid+" Deleted";
    }

    // 7. Update a person's data
    public Person update(Long uid, Person update){
        Person person = personRepository.findById(uid).orElse(null);
        if(person == null ){
            return person;
        }
        if(update.getFirstName() != null && !update.getFirstName().isEmpty()){
            person.setFirstName(update.getFirstName());
        }
        if(update.getLastName() != null && !update.getLastName().isEmpty()){
            person.setLastName(update.getLastName());
        }
        return personRepository.save(person);
        
    }

    // 10. search
    public List<Person> getAllByFirstName(String fn){
        return personRepository.findAllByFirstName(fn);
    }
}
