package io.dummymaker.export.validation;

import io.dummymaker.util.INameStrategist;
import io.dummymaker.util.NameStrategist;

import static io.dummymaker.data.Dummy.DummyFieldNames.*;
import static io.dummymaker.util.NameStrategist.NamingStrategy;
import static org.junit.Assert.assertTrue;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 01.09.2017
 */
public class SqlValidation {

    public void isSingleDummyValid(String[] dummy) {
        assertTrue(dummy[0].matches("CREATE TABLE IF NOT EXISTS dummy\\("));
        assertTrue(dummy[1].matches("\\t" + NAME.getExportFieldName() + "\\tVARCHAR,"));
        assertTrue(dummy[2].matches("\\t" + NUM.getExportFieldName() + "\\tINT,"));
        assertTrue(dummy[3].matches("\\t" + GROUP.getExportFieldName() + "\\tVARCHAR,"));
        assertTrue(dummy[4].matches("\\tPRIMARY KEY \\([a-zA-Z]+\\)"));
        assertTrue(dummy[5].matches("\\);"));

        assertTrue(dummy[6].matches(""));

        assertTrue(dummy[7].matches("INSERT INTO dummy \\(" + NAME.getExportFieldName() + ", " + NUM.getExportFieldName() + ", " + GROUP.getExportFieldName() + "\\) VALUES "));
        assertTrue(dummy[8].matches("\\('[a-zA-Z]+', null, '[0-9]+'\\);"));
    }

    public void isTwoDummiesValid(String[] dummies) {
        assertTrue(dummies[0].matches("CREATE TABLE IF NOT EXISTS dummy\\("));
        assertTrue(dummies[1].matches("\\t" + NAME.getExportFieldName() + "\\tVARCHAR,"));
        assertTrue(dummies[2].matches("\\t" + NUM.getExportFieldName() + "\\tINT,"));
        assertTrue(dummies[3].matches("\\t" + GROUP.getExportFieldName() + "\\tVARCHAR,"));
        assertTrue(dummies[4].matches("\\tPRIMARY KEY \\([a-zA-Z]+\\)"));
        assertTrue(dummies[5].matches("\\);"));

        assertTrue(dummies[6].matches(""));

        assertTrue(dummies[7].matches("INSERT INTO dummy \\(" + NAME.getExportFieldName() + ", " + NUM.getExportFieldName() + ", " + GROUP.getExportFieldName() + "\\) VALUES "));
        assertTrue(dummies[8].matches("\\('[a-zA-Z]+', [0-9]+, '[0-9]+'\\),"));
        assertTrue(dummies[9].matches("\\('[a-zA-Z]+', [0-9]+, '[0-9]+'\\);"));
    }

    public void isTwoDummiesValidithNamingStratery(String[] dummies, NamingStrategy strategy) {
        final INameStrategist strategist = new NameStrategist();

        final String expectedNameField = strategist.toNamingStrategy(NAME.getExportFieldName(), strategy);
        final String expectedGroupField = GROUP.getExportFieldName();
        final String expectedNumField = strategist.toNamingStrategy(NUM.getExportFieldName(), strategy);

        assertTrue(dummies[0].matches("CREATE TABLE IF NOT EXISTS dummy\\("));
        assertTrue(dummies[1].matches("\\t" + expectedNameField + "\\tVARCHAR,"));
        assertTrue(dummies[2].matches("\\t" + expectedNumField + "\\tINT,"));
        assertTrue(dummies[3].matches("\\t" + expectedGroupField + "\\tVARCHAR,"));
        assertTrue(dummies[4].matches("\\tPRIMARY KEY \\([a-zA-Z]+\\)"));
        assertTrue(dummies[5].matches("\\);"));

        assertTrue(dummies[6].matches(""));

        assertTrue(dummies[7].matches("INSERT INTO dummy \\(" + expectedNameField + ", " + expectedNumField + ", " + expectedGroupField + "\\) VALUES "));
        assertTrue(dummies[8].matches("\\('[a-zA-Z]+', [0-9]+, '[0-9]+'\\),"));
        assertTrue(dummies[9].matches("\\('[a-zA-Z]+', [0-9]+, '[0-9]+'\\);"));
    }
}
