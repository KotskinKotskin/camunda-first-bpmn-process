package bpmn2.storm.backend.service

import bpmn2.storm.backend.model.Person


interface PersonService {
    fun findOne(id: String): Person
    fun findAll(): List<Person>
    fun findByFirstName(firstName: String): List<Person>

    fun create(person: Person)
    fun update(person: Person)
    fun delete(person: Person)
}
