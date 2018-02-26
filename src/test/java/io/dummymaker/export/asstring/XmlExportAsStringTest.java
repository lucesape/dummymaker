package io.dummymaker.export.asstring;

import io.dummymaker.data.Dummy;
import io.dummymaker.export.IExporter;
import io.dummymaker.export.impl.StaticXmlExporter;
import io.dummymaker.export.naming.IStrategy;
import io.dummymaker.export.naming.PresetStrategies;
import io.dummymaker.export.validation.XmlValidation;
import io.dummymaker.factory.IProduceFactory;
import io.dummymaker.factory.impl.GenProduceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 20.08.2017
 */
public class XmlExportAsStringTest {

    private IProduceFactory produceFactory = new GenProduceFactory();

    private XmlValidation validation = new XmlValidation();

    @Test
    public void exportSingleDummyInXml() throws Exception {
        Dummy dummy = produceFactory.produce(Dummy.class);
        IExporter exporter = new StaticXmlExporter();

        String dummyAsString = exporter.exportAsString(dummy);
        assertNotNull(dummyAsString);

        String[] xmlArray = dummyAsString.split("\n");
        assertEquals(5, xmlArray.length);

        validation.isSingleDummyValid(xmlArray);
    }

    @Test
    public void exportListOfDummiesInXml() throws Exception {
        List<Dummy> dummies = produceFactory.produce(Dummy.class, 2);
        IExporter exporter = new StaticXmlExporter();

        String dummyAsString = exporter.exportAsString(dummies);
        assertNotNull(dummyAsString);

        String[] xmlArray = dummyAsString.split("\n");
        assertEquals(12, xmlArray.length);

        validation.isTwoDummiesValid(xmlArray);
    }

    @Test
    public void exportListOfDummiesInXmlWithNamingStrategy() throws Exception {
        final IStrategy strategy = PresetStrategies.INITIAL_LOW_CASE.getStrategy();

        List<Dummy> dummies = produceFactory.produce(Dummy.class, 2);
        IExporter exporter = new StaticXmlExporter().withStrategy(strategy);

        String dummyAsString = exporter.exportAsString(dummies);
        assertNotNull(dummyAsString);

        String[] xmlArray = dummyAsString.split("\n");
        assertEquals(12, xmlArray.length);

        validation.isTwoDummiesValidWithNamingStrategy(xmlArray, strategy);
    }
}
