package bpmn2.storm.backend.delegate

import bpmn2.storm.backend.model.LessonRequest
import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

class UpdateCounterDelegate: JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        var lesson = execution.getVariable("lesson") as LessonRequest
        lesson.firstCommunication = false
        var taskResult = execution.getVariable("taskResult")
        if (taskResult != "NonAnswer") { lesson.recallCount = lesson.recallCount!! + 1 }
        if (taskResult == "NonAnswer") { lesson.nonAnswerCount = lesson.nonAnswerCount!! + 1}
        if (taskResult == "RecallWithAgreement") lesson.haveAgreement = true
        execution.setVariable("lesson",lesson)

    }
}