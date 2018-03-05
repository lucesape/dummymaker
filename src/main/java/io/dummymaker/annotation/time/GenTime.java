package io.dummymaker.annotation.time;

import io.dummymaker.annotation.PrimeGenAnnotation;
import io.dummymaker.generator.impl.time.LocalDateTimeGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author GoodforGod
 * @since 06.03.2018
 */
@PrimeGenAnnotation(LocalDateTimeGenerator.class)
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GenTime {
    long MAX = 32503669200000L;

    long from() default 0L;

    long to() default MAX;
}