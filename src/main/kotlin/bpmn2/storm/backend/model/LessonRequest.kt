package bpmn2.storm.backend.model

import java.io.Serializable

class LessonRequest: Serializable {
    var gradation: String = "kid"
    var clientId: String =""
    var firstCommunication: Boolean = true
    var failedAssignation: Boolean = false
    var haveAgreement: Boolean = false
    var recallCount: Int? = 0
    var nonAnswerCount: Int? = 0
    var lessonTime: String? =""
    var bookingSystem: String? = ""
    var confirmed: Boolean = false


    companion object {
        private const val serialVersionUID: Long = 1239984434021739346
    }
}
