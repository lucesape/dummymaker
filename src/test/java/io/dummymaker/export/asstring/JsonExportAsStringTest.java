package io.dummymaker.export.asstring;

import io.dummymaker.data.Dummy;
import io.dummymaker.export.IExporter;
import io.dummymaker.export.impl.JsonExporter;
import io.dummymaker.factory.IProduceFactory;
import io.dummymaker.factory.impl.GenProduceFactory;
import org.junit.Test;

import java.util.List;

import static io.dummymaker.data.Dummy.DummyFieldNames.*;
import static org.junit.Assert.*;

/**
 * "Default Description"
 *
 * @author GoodforGod
 * @since 20.08.2017
 */
public class JsonExportAsStringTest {

    private IProduceFactory<Dummy> produceFactory = new GenProduceFactory<>(Dummy.class);

    @Test
    public void exportSingleDummyInJson() {
        Dummy dummy = produceFactory.produce();
        IExporter<Dummy> exporter = new JsonExporter<>(Dummy.class);

        String dummyAsJsonString = exporter.exportAsString(dummy);
        assertNotNull(dummyAsJsonString);

        String[] jsonArray = dummyAsJsonString.split("\n");
        assertEquals(5, jsonArray.length);

        assertTrue(jsonArray[0].matches("\\{"));
        assertTrue(jsonArray[1].matches("\\t\"" + NAME.getExportFieldName() + "\":\\s\"[a-zA-Z0-9]+\","));
        assertTrue(jsonArray[2].matches("\\t\"" + GROUP.getExportFieldName() + "\":\\s\"[0-9]+\","));
        assertTrue(jsonArray[3].matches("\\t\"" + NUM.getExportFieldName()  + "\":\\s\"null\""));
        assertTrue(jsonArray[4].matches("}"));
    }

    @Test
    public void exportListOfDummiesInJson() {
        List<Dummy> dummy = produceFactory.produce(2);
        IExporter<Dummy> exporter = new JsonExporter<>(Dummy.class);

        String dummyAsJsonString = exporter.exportAsString(dummy);
        assertNotNull(dummyAsJsonString);

        String[] jsonArray = dummyAsJsonString.split("\n");
        assertEquals(14, jsonArray.length);

        assertTrue(jsonArray[0].matches("\\{"));
        assertTrue(jsonArray[1].matches("\\t\"[a-zA-Z]+\": \\["));

        assertTrue(jsonArray[2].matches("\\t{2}\\{"));
        assertTrue(jsonArray[3].matches("\\t{3}\"" + NAME.getExportFieldName() + "\":\\s\"[a-zA-Z0-9]+\","));
        assertTrue(jsonArray[4].matches("\\t{3}\"" + GROUP.getExportFieldName() + "\":\\s\"[0-9]+\","));
        assertTrue(jsonArray[5].matches("\\t{3}\"" + NUM.getExportFieldName()  + "\":\\s\"[0-9]+\""));
        assertTrue(jsonArray[6].matches("\\t{2}},"));

        assertTrue(jsonArray[7].matches("\\t{2}\\{"));
        assertTrue(jsonArray[8].matches("\\t{3}\"" + NAME.getExportFieldName() + "\":\\s\"[a-zA-Z0-9]+\","));
        assertTrue(jsonArray[9].matches("\\t{3}\"" + GROUP.getExportFieldName() + "\":\\s\"[0-9]+\","));
        assertTrue(jsonArray[10].matches("\\t{3}\"" + NUM.getExportFieldName()  + "\":\\s\"[0-9]+\""));
        assertTrue(jsonArray[11].matches("\\t{2}}"));

        assertTrue(jsonArray[12].matches("\\t]"));
        assertTrue(jsonArray[13].matches("}"));
    }
}
