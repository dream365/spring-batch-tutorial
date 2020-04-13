package com.dream365.springbatch.job

import com.dream365.springbatch.logging.ILogging
import com.dream365.springbatch.logging.LoggingImpl
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepExecution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.job.flow.FlowExecutionStatus
import org.springframework.batch.core.job.flow.JobExecutionDecider
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class StepDeciderConfiguration(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) : ILogging by LoggingImpl<StepDeciderConfiguration>() {

    @Bean
    fun stepDeciderJob(): Job = jobBuilderFactory.get("stepDeciderJob")
        .start(startStep())
        .next(decider())
        .from(decider())
            .on("ODD")
            .to(oddStep())
        .from(decider())
            .on("EVEN")
            .to(evenStep())
        .end()
        .build()

    @Bean
    fun startStep(): Step = stepBuilderFactory.get("startStep")
        .tasklet { _, _ ->
            log.info(">>>>> Start Step")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean
    fun evenStep(): Step = stepBuilderFactory.get("evenStep")
        .tasklet { _, _ ->
            log.info(">>>>> Number is even")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean
    fun oddStep(): Step = stepBuilderFactory.get("oddStep")
        .tasklet { _, _ ->
            log.info(">>>>> Number is odd")
            RepeatStatus.FINISHED
        }
        .build()

    @Bean
    fun decider(): JobExecutionDecider = OddDecider()

    class OddDecider : JobExecutionDecider, ILogging by LoggingImpl<OddDecider>() {
        override fun decide(jobExecution: JobExecution, stepExecution: StepExecution?): FlowExecutionStatus {
            val randomNum = Random().nextInt(50) + 1
            log.info("Random Number : {}", randomNum)

            return if (randomNum % 2 == 0)
                FlowExecutionStatus("EVEN")
            else
                FlowExecutionStatus("ODD")
        }
    }
}