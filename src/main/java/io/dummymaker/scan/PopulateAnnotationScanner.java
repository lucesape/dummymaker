package io.dummymaker.scan;

import io.dummymaker.annotation.base.PrimeGenAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Scans for all fields annotated with super PrimeGenAnnotation annotations
 *
 * @see PrimeGenAnnotation
 *
 * @author GoodforGod
 * @since 29.05.2017
 */
public class PopulateAnnotationScanner extends AnnotationScanner {

    /**
     * Predicate to check for core prime gen annotation
     *
     * @see PrimeGenAnnotation
     */
    private final Predicate<Annotation> acceptPredicate = (a) -> a.annotationType().equals(PrimeGenAnnotation.class);

    @Override
    public Map<Field, Set<Annotation>> scan(final Class t) {
        final Map<Field, Set<Annotation>> classFieldAnnotations = super.scan(t);

        return (classFieldAnnotations.isEmpty())
                ? classFieldAnnotations
                : classFieldAnnotations.entrySet().stream()
                    .filter(set -> set.getValue().stream().anyMatch(acceptPredicate))
                    .peek(set -> set.setValue(set.getValue().stream().filter(acceptPredicate).collect(Collectors.toSet())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
