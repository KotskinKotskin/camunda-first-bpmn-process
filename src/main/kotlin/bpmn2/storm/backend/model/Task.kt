package bpmn2.storm.backend.model

import java.io.Serializable

class Task: Serializable{
    var type: String? = ""
    var poolName: String? = ""
    var taskResult: String = ""
    var normalWeight: Double? = null
    var deadlineWeight: Double? = null
    companion object {
        private const val serialVersionUID: Long = 123
    }

}