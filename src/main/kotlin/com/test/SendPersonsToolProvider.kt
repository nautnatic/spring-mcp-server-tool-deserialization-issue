package com.test

import org.springaicommunity.mcp.annotation.McpTool
import org.springframework.stereotype.Service

@Service
class SendPersonsToolProvider {
    @McpTool
    fun setPersons(persons: List<Person>) {
        println(persons)
    }
}