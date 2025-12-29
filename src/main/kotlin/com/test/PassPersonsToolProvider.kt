package com.test

import org.springaicommunity.mcp.annotation.McpTool
import org.springframework.stereotype.Service

@Service
class PassPersonsToolProvider {
    /**
     * Calling this tool works (throws no error) and shows that the deserialization of complex MCP tool parameters with Jackson works as expected.
     */
    @McpTool
    fun passJavaPersonsWithDefaultConstructor(
        persons: List<JavaPersonWithDefaultConstructor>) {
        println(persons)
    }

    /**
     * Calling this tool fails with the following error:
     * "Cannot construct instance of `com.test.KotlinPerson` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
     *  at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 3] (through reference chain: java.util.ArrayList[0])"
     */
    @McpTool
    fun passKotlinPersons(
        persons: List<KotlinPerson>) {
        println(persons)
    }

    /**
     * Calling this tool fails with the same error as "passKotlinPersons":
     * "Cannot construct instance of `com.test.JavaPersonWithoutDefaultConstructor` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
     *  at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1, column: 3] (through reference chain: java.util.ArrayList[0])"
     */
    @McpTool
    fun passJavaPersonsWithoutDefaultConstructor(
        persons: List<JavaPersonWithoutDefaultConstructor>) {
        println(persons)
    }
}