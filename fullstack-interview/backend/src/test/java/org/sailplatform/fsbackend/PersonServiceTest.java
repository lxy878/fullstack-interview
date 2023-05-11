package org.sailplatform.fsbackend;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sailplatform.fsbackend.model.Person;
import org.sailplatform.fsbackend.repository.PersonRepository;
import org.sailplatform.fsbackend.service.PersonService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    
    @Mock
    PersonRepository personRepository;
    
    @InjectMocks
    PersonService personService;
    
    @Test
    void testAdd() {
        Person personToAdd = new Person("John", "Doe");
        Person savedPerson = new Person(1L, "John", "Doe");
        when(personRepository.save(personToAdd)).thenReturn(savedPerson);
        
        Person result = personService.add(personToAdd);
        
        verify(personRepository).save(personToAdd);
        assertEquals(savedPerson, result);
    }
    
    @Test
    void testDelete() {
        Long uid = 1L;
        Optional<Person> personToDelete = Optional.of(new Person(uid, "John", "Doe"));
        when(personRepository.findById(uid)).thenReturn(personToDelete);
        
        String result = personService.delete(uid);
        
        verify(personRepository, times(1)).delete(personToDelete.get());
        assertEquals("Person Id: " + uid + " Deleted", result);
    }
    
    @Test
    void testUpdate() {
        Long uid = 1L;
        Person existingPerson = new Person(uid, "John", "Doe");
        Person updatedPerson = new Person(uid, "Jane", "Doe");
        when(personRepository.findById(uid)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(existingPerson)).thenReturn(updatedPerson);
        
        Person result = personService.update(uid, updatedPerson);
        
        verify(personRepository).findById(uid);
        verify(personRepository).save(existingPerson);
        assertEquals(updatedPerson, result);
    }

    @Test
    public void testFindAllByFirstName() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("John", "Doe"));
        persons.add(new Person("John", "Smith"));
        String firstName = "John";
        when(personRepository.findAllByFirstName(firstName)).thenReturn(persons);
        List<Person> result = personRepository.findAllByFirstName(firstName);
       
        verify(personRepository).findAllByFirstName(firstName);
        
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Smith", result.get(1).getLastName());
    }
}
