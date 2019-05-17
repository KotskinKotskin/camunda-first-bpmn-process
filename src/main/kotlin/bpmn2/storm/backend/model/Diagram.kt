package bpmn2.storm.backend.modelве

import org.hibernate.annotations.GenericGenerator
import java.io.Serializable
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity

class Diagram : Serializable {
    @Id

    val id: UUID = UUID.randomUUID()
    var XmlMody: String = ""
}