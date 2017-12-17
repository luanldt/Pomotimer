/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luandeptrai.controller;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author luannm
 */
public class Settings {

  private Document doc;
  private XPath xpath;
  
  public Settings() {
    this.loadSettingFile();
  }

  public String readProperty(String key) {
    String result = null;
    try {
      String xPath = key.replace(".", "/");
      XPathExpression expr = xpath.compile(xPath);
      result = expr.evaluate(doc);
      System.out.println(result);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean editProperty(String key, String value) {
    boolean result = false;
    try {
      String xPath = key.replace(".", "/");
      XPathExpression expr = xpath.compile(xPath);
      Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
      node.setTextContent(value);
      result = true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  public boolean saveSettings() {
    boolean result = false;
    try {
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult streamResult = new StreamResult(new File("settings.xml"));
      transformer.transform(source, streamResult);
      result = true;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  private void loadSettingFile() {
    try {
      File fXmlFile = new File("settings.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.parse(fXmlFile);
      XPathFactory xPathfactory = XPathFactory.newInstance();
      xpath = xPathfactory.newXPath();
      doc.getDocumentElement().normalize();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
