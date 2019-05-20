package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.LessonRequest
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.camunda.spin.DataFormats.json
import org.camunda.spin.Spin.S
import org.camunda.spin.json.SpinJsonNode



class PostExternalTaskDelegate: JavaDelegate {
    override fun execute(execution: DelegateExecution) {
        var result = execution.getVariable("Result")

        if (result != null) {
            val json = S(result, json())
            var lesson = execution.getVariable("lesson") as LessonRequest
            lesson.lessonTime = json.prop("lessonTime").value() as String
            lesson.bookingSystem = json.prop("bookingSystem").value() as String
            lesson.confirmed = json.prop("isConfirmed").value() as Boolean
            execution.setVariable("lesson", lesson)
        }
    }
}