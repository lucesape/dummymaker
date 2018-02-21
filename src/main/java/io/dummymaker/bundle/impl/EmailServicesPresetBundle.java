package io.dummymaker.bundle.impl;

/**
 * Contains EMAIL service provides as string
 *
 * @author GoodforGod
 * @since 30.05.2017
 */
public class EmailServicesPresetBundle extends BasicBundle<String> {

    public EmailServicesPresetBundle() {
        super(
            "zoho",
            "yandex",
            "mail",
            "gmail",
            "outlook",
            "proton",
            "aim",
            "icloud",
            "yahoo",
            "custom",
            "aol",
            "gmx",
            "hotmail",
            "inbox"
        );
    }
}