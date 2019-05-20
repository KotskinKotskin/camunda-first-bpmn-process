package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.LessonRequest
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

class PreDecisionDelegate: JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        var lesson = LessonRequest().also{it.recallCount = 1}
        if (!execution.hasVariable("lesson")) {
            execution.setVariable("lesson", lesson)
            if (execution.variableNames.contains("CalculateTaskPropsResult")) execution.removeVariable("CalculateTaskPropsResult")
            println(lesson.toString())
        }
    }
}