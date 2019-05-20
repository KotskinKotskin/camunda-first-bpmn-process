package bpmn2.storm.backend.model

import java.io.Serializable

class LessonRequest: Serializable {
    var gradation: String = "kid"
    var firstCommunication: Boolean = true
    var failedAssignation: Boolean = false
    var haveAgreement: Boolean = false
    var recallCount: Int? = 0
    var nonAnswerCount: Int? = 0

}
