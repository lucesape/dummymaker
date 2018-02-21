package io.dummymaker.annotation.time;

import io.dummymaker.annotation.PrimeGenAnnotation;
import io.dummymaker.generator.impl.time.LocalTimeGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see LocalTimeGenerator
 *
 * @author GoodforGod
 * @since 21.02.2018
 */
@PrimeGenAnnotation(LocalTimeGenerator.class)
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface GenLocalTime {

}