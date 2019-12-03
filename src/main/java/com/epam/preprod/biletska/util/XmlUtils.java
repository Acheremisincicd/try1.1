package com.epam.preprod.biletska.util;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {

    private static final Logger LOGGER = Logger.getLogger(XmlUtils.class);

    private XmlUtils() {
    }

    public static  Map<String, List<String>> parseXML(String pathToXML) {
        Map<String, List<String>> constraints = new HashMap<>();
        File inputFile = new File(pathToXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = dbFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("constraint");
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);

                String role = element.getElementsByTagName("role").item(0).getTextContent();
                constraints.put(role, new ArrayList<>());

                NodeList urlPatterns = element.getElementsByTagName("url-pattern");
                for (int j = 0; j < urlPatterns.getLength(); j++) {
                    Element elem = (Element) element.getElementsByTagName("url-pattern").item(j);
                    constraints.get(role).add(elem.getTextContent());
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException exception) {
            LOGGER.warn("Failed to parse security.xml file.", exception);
        }
        return constraints;
    }
}
