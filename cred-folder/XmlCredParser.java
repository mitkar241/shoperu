import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

class XmlCredParser{

public static void main(String args[]) throws Exception{
  //Get Document Builder
  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  DocumentBuilder builder = factory.newDocumentBuilder();
  
  //Build Document
  Document document = builder.parse(new File("EmployeeCred.xml"));
  
  //Normalize the XML Structure; It's just too important !!
  document.getDocumentElement().normalize();

  //Here comes the root node
  Element root = document.getDocumentElement();
  System.out.println(root.getNodeName());
  
  //Get all employees
  NodeList nList = document.getElementsByTagName("employee");
  
  System.out.println(nList.getLength());
  for (int temp = 0; temp < nList.getLength(); temp++)
  {
    Node node = nList.item(temp);
    System.out.println("");    //Just a separator
    if (node.getNodeType() == Node.ELEMENT_NODE)
    {
        //Print each employee's detail
        Element eElement = (Element) node;
        System.out.println("Employee id : "    + eElement.getAttribute("id"));
        System.out.println("EmpId : "  + eElement.getElementsByTagName("EmpId").item(0).getTextContent());
        System.out.println("PwdHash : "   + eElement.getElementsByTagName("PwdHash").item(0).getTextContent());
        System.out.println("ExpDate : "   + eElement.getElementsByTagName("ExpDate").item(0).getTextContent());
    }
  }
}

}
