package com.dream365.springbatch.job

import com.dream365.springbatch.logging.ILogging
import com.dream365.springbatch.logging.LoggingImpl
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SimpleJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) : ILogging by LoggingImpl<SimpleJobConfiguration>() {
    @Bean
    fun simpleJob(): Job = jobBuilderFactory.get("simpleJob")
        .start(simpleStep())
        .build()

    fun simpleStep(): Step = stepBuilderFactory.get("simpleStep")
        .tasklet { _, _ ->
            log.info(">>> Simple step")
            RepeatStatus.FINISHED
        }.build()
}