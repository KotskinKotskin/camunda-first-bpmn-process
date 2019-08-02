package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.service.PersonRepositoryService
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("getEntityDelegate")
class readEntityDelegate(private val personRepo: PersonRepositoryService): JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        var id = execution.getVariable("id") as String

        var person = personRepo.findOne(id)

        execution.setVariable("firstName", person.firstName)

    }
}
