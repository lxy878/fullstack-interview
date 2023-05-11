package org.sailplatform.fsbackend.controller;
import java.util.List;

import org.sailplatform.fsbackend.model.Person;
import org.sailplatform.fsbackend.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = { "*"})
public class PersonController {

    @Autowired PersonService personService;

    @GetMapping("/all")
	public List<Person> getAll() {
		return personService.getAll();
	}

    @PostMapping("/add")
	public Person add(@RequestBody Person toAdd) {
		return personService.add(toAdd);
	}

	@GetMapping("/{uid}")
	public ResponseEntity<Object> get(@PathVariable Long uid){
		Person person = personService.getOne(uid);
		return person != null? 
			ResponseEntity.ok().body(person) : 
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person is Not Found");
	}

	// 7. delete
	@DeleteMapping("/{uid}")
	public String delete(@PathVariable Long uid){
		return personService.delete(uid);
	}

	// 7. update
	@PatchMapping("/edit/{uid}")
	public ResponseEntity<Object> update(@PathVariable Long uid, @RequestBody Person toUpdate){
		Person person = personService.update(uid, toUpdate);
		
		return person != null? 
			ResponseEntity.ok().body(person) :
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person is Not Found");
	}

	// 10. search
	@GetMapping("/search")
	public List<Person> getAllByFirstName(@RequestParam(name="term") String fn){
		if(fn == null || fn.isEmpty()){
            return personService.getAll();
        }
		return personService.getAllByFirstName(fn);
	}
	
}
