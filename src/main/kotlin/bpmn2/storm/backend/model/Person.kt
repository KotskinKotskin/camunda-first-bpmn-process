package bpmn2.storm.backend.model

import com.couchbase.client.java.repository.annotation.Field
import org.joda.time.DateTime
import org.springframework.data.annotation.Id
import org.springframework.data.couchbase.core.mapping.Document
import java.io.Serializable
import javax.validation.constraints.NotNull


@Document
class Person (id: String?, firstName: String?, lastName: String? ) {
    @Id
    @Field
     val id: String? = id

    @Field
     val firstName: String? = firstName

    @Field
     val lastName: String? = lastName

    @Field
     val created: DateTime? = null

    @Field
     val updated: DateTime? = null


}
