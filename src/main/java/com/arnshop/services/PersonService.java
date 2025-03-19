package com.arnshop.services;

import com.arnshop.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> people = new ArrayList<>();

    public List<Person> getAllPeople() {
        return people;
    }

    public Person getPersonById(Long id) {
        return people.stream().filter(person -> person.getId().equals(id)).findFirst().orElse(null);
    }

    public Person createPerson(Person person) {
        people.add(person);
        return person;
    }

    public Person updatePerson(Long id, Person personDetails) {
        Person person = getPersonById(id);
        if (person != null) {
            person.setName(personDetails.getName());
            person.setEmail(personDetails.getEmail());
        }
        return person;
    }

    public boolean deletePerson(Long id) {
        return people.removeIf(person -> person.getId().equals(id));
    }
}
