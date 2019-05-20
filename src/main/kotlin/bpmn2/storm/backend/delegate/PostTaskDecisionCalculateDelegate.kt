package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.Task
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

class PostTaskDecisionCalculateDelegate: JavaDelegate {

    override fun execute(execution: DelegateExecution) {

        var CalculateTaskPropsResult = execution.getVariable("CalculateTaskPropsResult") as Map<String, Any>
        var task = Task()
        task.poolName = CalculateTaskPropsResult["poolName"] as String
        task.type = CalculateTaskPropsResult["type"] as String
        task.normalWeight = CalculateTaskPropsResult["normalWeight"] as Double
        task.deadlineWeight = CalculateTaskPropsResult["deadlineWeight"] as Double
        execution.setVariable("task",task)


    }
}