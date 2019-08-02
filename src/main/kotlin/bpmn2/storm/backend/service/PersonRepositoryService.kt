package bpmn2.storm.backend.service

import bpmn2.storm.backend.repository.PersonRepository
import bpmn2.storm.backend.model.Person
import java.util.ArrayList
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


@Service
@Qualifier("PersonRepositoryService")
class PersonRepositoryService(private val repo: PersonRepository) : PersonService {


    override fun findOne(id: String): Person {
        return repo!!.findById(id).get()
    }

    override fun findAll(): List<Person> {
        val people = ArrayList<Person>()
        val it = repo!!.findAll().iterator()
        while (it.hasNext()) {
            people.add(it.next())
        }
        return people
    }

    override fun findByFirstName(firstName: String): List<Person> {
        return repo!!.findByFirstName(firstName)
    }

    override fun create(person: Person) {

        repo!!.save(person)
    }

    override fun update(person: Person) {
         repo!!.save(person)
    }

    override fun delete(person: Person) {
        repo!!.delete(person)
    }
}
