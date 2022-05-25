import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

class XmlMySqlParser{

public static void main(String args[]) throws Exception{
  //Get Document Builder
  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  DocumentBuilder builder = factory.newDocumentBuilder();
  
  //Build Document
  Document document = builder.parse(new File("MySqlCred.xml"));
  
  /*
  System.out.println(document.getClass());
  System.out.println(document.getClass().getName());
  System.out.println(document.getClass().getSimpleName());
  */
  
  //Normalize the XML Structure; It's just too important !!
  document.getDocumentElement().normalize();

  //Here comes the root node
  Element root = document.getDocumentElement();
  //System.out.println(root.getNodeName());
  
  //Get all employees
  NodeList nList = document.getElementsByTagName("mysql");
  
  for (int temp = 0; temp < nList.getLength(); temp++)
  {
    Node node = nList.item(temp);
    //System.out.println("");    //Just a separator
    if (node.getNodeType() == Node.ELEMENT_NODE)
    {
        //Print each employee's detail
        Element eElement = (Element) node;
        //System.out.println("Employee id : "    + eElement.getAttribute("id"));
        System.out.println("First Name : "  + eElement.getElementsByTagName("username").item(0).getTextContent());
        System.out.println("Last Name : "   + eElement.getElementsByTagName("password").item(0).getTextContent());
    }
  }
}

}
