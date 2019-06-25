package ru.job4j.sql;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс для преобразования xml-файлов в другой формат.
 * @author Evgeniya Tsiurupa
 * @version 1.0
 * @since 24/06/2019
 */

public class ConvertXSQT {
    /**
     * Метод преобразует xml-файл в другой формат.
     * @param source исходный файл.
     * @param dest файл для записи результата.
     * @param scheme XSTL схема.
     */
    public void convert(File source, File dest, File scheme) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(scheme));
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        File source = new File("C:\\projects\\job4j\\chapter_007\\src\\main\\resources\\storeXML.xml");
        File dest = new File("C:\\projects\\job4j\\chapter_007\\src\\main\\resources\\storeNewXML.xml");

        String xsl = "<?xml version=\"1.0\"?>\n"
                + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "<xsl:template match=\"/\">\n"
                + "<entries>"
                + "   <xsl:for-each select=\"entries/list\">\n"
                + "       <entry>"
                + "           <xsl:attribute name=\"field\">"
                + "               <xsl:value-of select=\"field\"/>"
                + "           </xsl:attribute>"
                + "       </entry>\n"
                + "   </xsl:for-each>\n"
                + " </entries>\n"
                + "</xsl:template>\n"
                + "</xsl:stylesheet>\n";
        try (PrintWriter out = new PrintWriter("chapter_007\\src\\main\\resources\\scheme.xsl")) {
            out.println(xsl);
        }

        File scheme = new File("C:\\projects\\job4j\\chapter_007\\src\\main\\resources\\scheme.xsl");
        ConvertXSQT conv = new ConvertXSQT();
        conv.convert(source, dest, scheme);
    }
}
