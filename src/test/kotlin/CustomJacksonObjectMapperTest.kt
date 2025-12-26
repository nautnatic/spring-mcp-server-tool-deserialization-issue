import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.core.type.TypeReference
import com.test.JacksonObjectMapper
import com.test.Person
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.Instant
import kotlin.test.assertTrue

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [JacksonObjectMapper::class])
class CustomJacksonObjectMapperTest {

    @Autowired
    private lateinit var mapper: ObjectMapper

    // class to deserialize into / serialize from
    data class Sample(val start: Instant)
    
    @Test
    fun `should deserialize Kotlin data classes`() {
        // given
        val json = """
            [
              {"name":"user","age":1},
              {"name":"user3","age":2}
            ]
        """.trimIndent()

        // when
        val typeRef = object : TypeReference<List<Person>>() {}
        val messages: List<Person> = mapper.readValue(json, typeRef)

        // then
        assertTrue { messages.size == 2 }
        assertEquals(Person(name = "user", age = 1), messages[0])
        assertEquals(Person(name = "user3", age = 2), messages[1])
    }
}