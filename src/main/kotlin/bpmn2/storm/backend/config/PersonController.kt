package bpmn2.storm.backend.config

import bpmn2.storm.backend.service.PersonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.ws.rs.PathParam

@RestController
@RequestMapping("/api/v1/person")
class PersonController(private val personService: PersonService) {

    @GetMapping()
    fun findAll() = personService.findAll()


}
