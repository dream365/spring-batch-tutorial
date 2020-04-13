package com.dream365.springbatch.logging

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface ILogging {
    val log: Logger
}

class LoggingImpl(loggerImpl: Logger) : ILogging {
    override val log: Logger = loggerImpl

    companion object {
        operator inline fun <reified T> invoke(): LoggingImpl {
            return LoggingImpl(LoggerFactory.getLogger(T::class.java))
        }
    }
}