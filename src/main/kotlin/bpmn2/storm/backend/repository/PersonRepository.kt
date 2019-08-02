package bpmn2.storm.backend.repository

import bpmn2.storm.backend.model.Person
import org.springframework.data.couchbase.core.query.ViewIndexed
import org.springframework.data.repository.CrudRepository

@ViewIndexed(designDoc = "person", viewName = "all")
interface PersonRepository : CrudRepository<Person, String> {
    fun findByFirstName(firstName: String): List<Person>
}
