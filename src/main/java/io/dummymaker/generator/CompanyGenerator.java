package io.dummymaker.generator;

import io.dummymaker.bundle.CompanyPresetBundle;

/**
 * Generates company name as a string
 *
 * @author GoodforGod (Anton Kurako)
 * @since 07.06.2017
 */
public class CompanyGenerator implements IGenerator<String> {

    @Override
    public String generate() {
        return new CompanyPresetBundle().getRandom();
    }
}
