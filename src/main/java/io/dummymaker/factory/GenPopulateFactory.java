package io.dummymaker.factory;

import io.dummymaker.annotation.base.PrimeGenAnnotation;
import io.dummymaker.annotation.special.GenEnumerate;
import io.dummymaker.scan.EnumerateAnnotationScanner;
import io.dummymaker.scan.IFieldScanner;
import io.dummymaker.scan.PopulateAnnotationScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Populates objects via PrimeGenAnnotation generators included
 *
 * @see PrimeGenAnnotation
 * @see GenEnumerate
 *
 * @author GoodforGod
 * @since 30.05.2017
 */
public class GenPopulateFactory<T> implements IPopulateFactory<T> {

    private final Logger logger = Logger.getLogger(GenPopulateFactory.class.getName());

    private final IFieldScanner populateScanner = new PopulateAnnotationScanner();
    private final IFieldScanner numerateAnnotationScanner = new EnumerateAnnotationScanner();

    private final Map<Field, Long> numerateFields = new HashMap<>();
    private boolean enableNumeration = false;

    @Override
    public T populate(final T t) {
        final Map<Field, Set<Annotation>> classAnnotatedFields = populateScanner.scan(t.getClass());

        for(Map.Entry<Field, Set<Annotation>> annotatedField : classAnnotatedFields.entrySet()) {
            Object objValue = null;
            try {
                annotatedField.getKey().setAccessible(true);

                if(enableNumeration && numerateFields.containsKey(annotatedField.getKey())) {
                    objValue = numerateFields.get(annotatedField.getKey());

                    if(annotatedField.getKey().getType().isAssignableFrom(Integer.class))
                        objValue = Integer.valueOf(String.valueOf(objValue));

                    incrementNumerateValue(annotatedField.getKey());
                }
                else
                    objValue = ((PrimeGenAnnotation) annotatedField.getValue().iterator().next()).value().newInstance().generate();

                annotatedField.getKey().set(t, annotatedField.getKey().getType().cast(objValue));
            }
            catch (IllegalAccessException e) {
                logger.warning(e.getMessage());
            }
            catch (ClassCastException e) {
                try {
                    if(annotatedField.getKey().getType().isAssignableFrom(String.class))
                        annotatedField.getKey().set(t, String.valueOf(objValue));
                }
                catch (Exception ex) {
                    logger.warning("FIELD TYPE AND GENERATE TYPE ARE NOT COMPATIBLE AND CAN NOT BE CONVERTED TO STRING.");
                }
            }
            catch (Exception e) {
                logger.warning(e.getMessage());
            }
            finally {
                annotatedField.getKey().setAccessible(false);
            }
        }

        return t;
    }

    @Override
    public List<T> populate(final List<T> t) {
        if(t == null || t.isEmpty())
            return new ArrayList<>();

        setupNumerateMap(t.get(0).getClass());

        final List<T> tList = t.stream().map(this::populate).collect(Collectors.toList());

        numerateFields.clear();

        return tList;
    }

    /**
     * Setup map
     * @param t class to scan for numerate fields
     */
    private void setupNumerateMap(final Class t) {
        final Map<Field, Set<Annotation>> numerateAnnotations = numerateAnnotationScanner.scan(t);

        if(numerateAnnotations != null && !numerateAnnotations.isEmpty()) {
            this.enableNumeration = true;

            for(Map.Entry<Field, Set<Annotation>> numerated : numerateAnnotations.entrySet()) {
                Long numerateValue = ((GenEnumerate) numerated.getValue().iterator().next()).from();
                this.numerateFields.put(numerated.getKey(), numerateValue);
            }
        }
    }

    /**
     * Increment numerate number for generated field
     * @param field field to increment
     */
    private void incrementNumerateValue(final Field field) {
        Long numerateValue = numerateFields.get(field);
        numerateValue++;
        numerateFields.replace(field, numerateValue);
    }
}
