package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.LessonRequest
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

class PreDecisionDelegate: JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        var lesson = LessonRequest()
        execution.setVariable("lesson",lesson)
        println(lesson.toString())
    }
}