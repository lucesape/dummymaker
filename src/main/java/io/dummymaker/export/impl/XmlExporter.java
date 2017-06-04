package io.dummymaker.export.impl;

import io.dummymaker.export.ExportType;
import io.dummymaker.export.OriginExporter;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Default Comment
 *
 * @author @GoodforGod
 * @since 26.05.2017
 */
public class XmlExporter<T>  extends OriginExporter<T> {

    private enum Mode {
        SINGLE,
        LIST
    }

    public XmlExporter(Class<T> primeClass) {
        super(primeClass, ExportType.XML);
    }

    public XmlExporter(Class<T> primeClass, String path) {
        super(primeClass, path, ExportType.XML);
    }

    private String wrapOpenXmlTag(String value) {
        return "<" + value + ">";
    }

    private String wrapCloseXmlTag(String value) {
        return "</" + value + ">";
    }

    private String objectToXml(T t, Mode mode) {
        Iterator<Map.Entry<String, String>> iterator = getExportValues(t).entrySet().iterator();

        StringBuilder builder = new StringBuilder("");

        String tabObject = (mode == Mode.SINGLE)
                ? ""
                : "\t";

        String tabField = (mode == Mode.SINGLE)
                ? "\t"
                : "\t\t";

        if(iterator.hasNext()) {

            builder.append(tabObject).append(wrapOpenXmlTag(primeClass.getSimpleName()));

            while (iterator.hasNext()) {
                Map.Entry<String, String> field = iterator.next();
                builder.append("\n").append(tabField)
                                    .append(wrapOpenXmlTag(field.getKey()))
                                    .append(field.getValue())
                                    .append(wrapCloseXmlTag(field.getKey()));
            }

            builder.append("\n").append(tabObject).append(wrapCloseXmlTag(primeClass.getSimpleName()));
        }

        return builder.toString();
    }

    @Override
    public void export(T t) {
        try {
            writeLine(objectToXml(t, Mode.SINGLE));
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }
        finally {
            try {
                flush();
            } catch (IOException e) {
                logger.warning(e.getMessage());
            }
        }
    }

    @Override
    public void export(List<T> list) {
        try {
            String XML_LIST_SUFFIX = "List";
            String superList = primeClass.getSimpleName() + XML_LIST_SUFFIX;
            writeLine(wrapOpenXmlTag(superList));

            for(T t : list)
                writeLine(objectToXml(t, Mode.LIST));

            writeLine(wrapCloseXmlTag(superList));
        }
        catch (IOException e) {
            logger.warning(e.getMessage());
        }
        finally {
            try {
                flush();
            } catch (IOException e) {
                logger.warning(e.getMessage());
            }
        }
    }
}