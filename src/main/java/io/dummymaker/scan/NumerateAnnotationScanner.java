package io.dummymaker.scan;

import io.dummymaker.annotation.special.GenEnumerate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Default Comment
 *
 * @author GoodforGod (Anton Kurako)
 * @since 07.06.2017
 */
public class NumerateAnnotationScanner extends AnnotationScanner {

    private final Predicate<Annotation> numeratePredicate = (a) -> a.annotationType().equals(GenEnumerate.class);

    @Override
    public Map<Field, Set<Annotation>> scan(Class t) {
        final Map<Field, Set<Annotation>> mapToFilter = super.scan(t);

        return (!mapToFilter.isEmpty())
                ? mapToFilter.entrySet().stream()
                    .filter(entry -> entry.getValue().stream().anyMatch(numeratePredicate))
                    .map(set -> {
                        set.setValue(set.getValue().stream().filter(numeratePredicate).collect(Collectors.toSet()));
                        return set;
                    })
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))

                : mapToFilter;
    }
}
