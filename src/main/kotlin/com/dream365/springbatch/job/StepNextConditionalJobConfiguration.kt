package com.dream365.springbatch.job

import com.dream365.springbatch.logging.ILogging
import com.dream365.springbatch.logging.LoggingImpl
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StepNextConditionalJobConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) : ILogging by LoggingImpl<StepNextConditionalJobConfiguration>() {
    @Bean
    fun stepNextConditionalJob(sc1: Step, sc2: Step, sc3: Step): Job = jobBuilderFactory.get("stepNextConditionalJob")
        .start(sc1)
            .on("FAILED")
            .to(sc3)
            .on("*")
            .end()
        .from(sc1)
            .on("*")
            .to(sc2)
            .next(sc3)
            .on("*")
            .end()
        .end()
        .build()

    @Bean(name = ["sc1"])
    fun step1(): Step = stepBuilderFactory.get("step1")
        .tasklet { contribution, _ ->
            log.info(">>>>> This is Step 1")

            contribution.exitStatus = ExitStatus.FAILED

            RepeatStatus.FINISHED
        }
        .build()

    @Bean(name = ["sc2"])
    fun step2(): Step = stepBuilderFactory.get("step2")
        .tasklet { contribution, _ ->
            log.info(">>>>> This is Step 2")

            RepeatStatus.FINISHED
        }
        .build()

    @Bean(name = ["sc3"])
    fun step3(): Step = stepBuilderFactory.get("step32")
        .tasklet { contribution, _ ->
            log.info(">>>>> This is Step 3")

            RepeatStatus.FINISHED
        }
        .build()
}