package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.Person
import bpmn2.storm.backend.service.PersonRepositoryService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component
import java.util.Random

@Component("writeEntityDelegate")
class EntityWriterDelegate(private val personRepo: PersonRepositoryService) : JavaDelegate {

    override fun execute(execution : DelegateExecution) {

       var id =  Random().nextInt().toString()
       var person = Person(id,"Denis", "Kotov")

        personRepo.create(person)
        execution.setVariable("id", id)
        execution.setVariable("personOn", person)

    }
}
