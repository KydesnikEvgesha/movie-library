package ru.evgeniykravchenko.movielibrary.common.aspect

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.aop.support.AopUtils
import org.springframework.stereotype.Component
import ru.evgeniykravchenko.movielibrary.common.annotation.LogExecution

@Aspect
@Component
class LogAspect {

    /**
     * Логирование вызываемых функций. Работает над теми методами где указана аннотация [LogExecution]
     *
     * @param joinPoint точка вызова метода
     * @return результат вызова метода
     */
    @Around("@annotation(ru.evgeniykravchenko.movielibrary.common.annotation.LogExecution)")
    fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any {
        val logger = KotlinLogging.logger(AopUtils.getTargetClass(joinPoint.`this`).toString())
        val signature = joinPoint.signature.name
        val start = System.currentTimeMillis()
        val result = try {
            with(StringBuilder("Called: $signature, ")) {
                appendParameters(joinPoint.args)
                logger.info(toString())
            }
            joinPoint.proceed()
        } catch (throwable: Throwable) {
            logger.error("Exception: $signature,", throwable)
            throw throwable
        }
        val duration = System.currentTimeMillis() - start
        logger.info("Result: $signature, returned: '$result', duration: $duration ms")
        return result
    }

    private fun StringBuilder.appendParameters(args: Array<Any>) {
        append("parameters: [")
        args.forEachIndexed { i, p ->
            append(p.javaClass.simpleName).append("(").append(p.toString()).append(")")
            if (i < args.size - 1) append(", ")
        }
        append("]")
    }
}