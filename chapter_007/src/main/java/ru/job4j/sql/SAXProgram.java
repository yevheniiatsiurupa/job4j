package ru.job4j.sql;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

public class SAXProgram {
    private static int sum = 0;

    public static int getSum() {
        return sum;
    }

    public static void main(String[] args) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("C:\\projects\\job4j\\chapter_007\\src\\main\\resources\\storeNewXML.xml"), handler);
        System.out.println(sum);
    }

    public static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                int tmp = Integer.parseInt(attributes.getValue("field"));
                sum += tmp;
            }
        }
    }
}
