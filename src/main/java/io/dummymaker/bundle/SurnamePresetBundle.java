package io.dummymaker.bundle;

import java.util.ArrayList;

/**
 * Default Comment
 *
 * @author @GoodforGod
 * @since 30.05.2017
 */
class SurnamePresetBundle extends IPresetBundle<String> {

    public SurnamePresetBundle() {
        super(new ArrayList<String>() {{
            add("");
        }});
    }
}
