package bpmn2.storm.backend.model

import com.couchbase.client.java.repository.annotation.Field
import org.joda.time.DateTime
import org.springframework.data.annotation.Id
import org.springframework.data.couchbase.core.mapping.Document
import java.io.Serializable
import javax.validation.constraints.NotNull


@Document
data class Person (@Id @Field
                   val id: String?,
                   @Field
                   val firstName: String?,
                   @Field
                   val lastName: String? ) {



}
