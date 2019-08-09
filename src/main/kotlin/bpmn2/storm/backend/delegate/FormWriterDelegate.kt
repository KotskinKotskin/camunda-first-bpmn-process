package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.Person
import bpmn2.storm.backend.service.PersonRepositoryService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component


@Component("formWriterDelegate")
class FormWriterDelegate(private val personRepo: PersonRepositoryService): JavaDelegate {
    override fun execute(execution: DelegateExecution) {
      var person = execution.getVariable("personOn") as Person
        personRepo.update(person)
    }
}
