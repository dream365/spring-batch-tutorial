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
class SimpleNextJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) : ILogging by LoggingImpl<SimpleNextJobConfiguration>() {
    @Bean
    fun stepNextJob(s1: Step, s2: Step, s3: Step): Job = jobBuilderFactory.get("stepNextJob")
        .start(s1)
        .next(s2)
        .next(s3)
        .build()

    @Bean(name = ["s1"])
    fun step1(): Step = stepBuilderFactory.get("step1")
        .tasklet { _, _ ->
            log.info(">>>>> This is Step 1")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean(name = ["s2"])
    fun step2(): Step = stepBuilderFactory.get("step2")
        .tasklet { _, _ ->
            log.info(">>>>> This is Step 2")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean(name = ["s3"])
    fun step3(): Step = stepBuilderFactory.get("step3")
        .tasklet { _, _ ->
            log.info(">>>>> This is Step 3")
            RepeatStatus.FINISHED
        }
        .build()
}