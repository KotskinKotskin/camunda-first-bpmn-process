package bpmn2.storm.backend.model

import java.io.Serializable

class Task: Serializable{
    val task: String = ""
    val poolName: String = ""
    val normalWeight: Int? = null
    val deadlineWeight: Int? = null


}