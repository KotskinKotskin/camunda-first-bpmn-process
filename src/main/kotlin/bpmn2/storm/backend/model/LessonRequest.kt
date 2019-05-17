package bpmn2.storm.backend.model

import java.io.Serializable

class LessonRequest: Serializable {
    var gradation: String = "test"
    var FirstCommunication: Boolean = true
    var FailedAssignation: Boolean = false
    var HaveAgreement: Boolean = false
    var recallCount: Int? = 0
    var nonAnswerCount: Int? = 0

}