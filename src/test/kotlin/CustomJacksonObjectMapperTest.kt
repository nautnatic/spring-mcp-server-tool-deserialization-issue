import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.test.KotlinPerson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CustomJacksonObjectMapperTest {
    val json = """
            [
              {"name":"user","age":1},
              {"name":"user3","age":2}
            ]
        """.trimIndent()
    val typeRef = object : TypeReference<List<KotlinPerson>>() {}

    /**
     * This test works for me and shows that the Jackson deserialization of Kotlin data classes works per se
     */
    @Test
    fun readValue_shouldDeserializationOfKotlinDataClasses_ifJacksonKotlinModuleIsRegistered() {
        // this uses a Jackson ObjectMapper that is configured with the Kotlin module
        val objectMapper = jacksonObjectMapper()

        val messages: List<KotlinPerson> = objectMapper.readValue(json, typeRef)

        // asserts
        assertTrue { messages.size == 2 }
        assertEquals(KotlinPerson(name = "user", age = 1), messages[0])
        assertEquals(KotlinPerson(name = "user3", age = 2), messages[1])
    }

    /**
     * This test throws the same issue as the MCP tool with the Kotlin data class parameter, indicating that the Kotlin module is not registered correctly.
     */
    @Test
    fun readValue_shouldFailOnDeserializationOfKotlinDataClasses_ifJacksonKotlinModuleIsNotRegistered() {
        // this uses a Jackson ObjectMapper that is configured with the Kotlin module
        val objectMapper = JsonMapper()

        val exception = assertThrows<InvalidDefinitionException> {
            objectMapper.readValue(json, typeRef)
        }

        val errorMessage = """
        Cannot construct instance of `com.test.KotlinPerson` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
         at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 2, column: 4] (through reference chain: java.util.ArrayList[0])
        """.trimIndent()

        assertEquals(errorMessage, exception.message)
    }
}