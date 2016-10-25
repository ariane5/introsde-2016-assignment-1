package XPath;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test{

    Document doc;
    XPath xpath;

    public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("people.xml");

        //creating xpath object
        getXPathObj();
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }
    
     // 1) Get weight

    public Node getWeight(String personID) throws XPathExpressionException {

      
        XPathExpression expr = xpath.compile("//person[@id='"+ personID + "']/healthprofile/weight");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


     // 2) Get height
public Node getHeight(String personID) throws XPathExpressionException {

       
        XPathExpression expr = xpath.compile("//person[@id='"+ personID + "']/healthprofile/height");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


      // 3) Get all people
 public NodeList getAllPeople() throws XPathExpressionException{
        XPathExpression expr = xpath.compile("/people");
        //Object result = expr.evaluate(doc, XPathConstants.NODESET);
        Node result =(Node) expr.evaluate(doc, XPathConstants.NODE);
	NodeList nodes = (NodeList) result;
        //NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);;
        return nodes;
}


    // 4) Get Healthprofile

public Node getHealthprofile(String personID) throws XPathExpressionException {

      
        XPathExpression expr = xpath.compile("//person[@id='" + personID + "']/healthprofile");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }


   //A function which accepts a weight and an operator (=, > , <) as parameters and prints people that fulfill that condition
    public NodeList getPeopleByWeight(String weight, String condition) throws XPathExpressionException {
        
        /*XPathExpression expr = xpath.compile("//book[price " + condition + "'" + price + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;*/
        XPathExpression expr = xpath.compile("//person[healthprofile/weight " + condition + "'" + weight + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
      return nodes;
    }


public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

       Test test = new Test();
        test.loadXML();


        //getting weight by ID number
        Node node = test.getWeight("0001");
        System.out.println("\nthe weight of person with id 0001\n");
        System.out.println(node.getTextContent());
        
        //getting height by ID number
        Node node1 = test.getHeight("0003");
        System.out.println("\nthe height of person with id 0003\n");
        System.out.println(node1.getTextContent());
       
        //getting all people
        NodeList node2 = test.getAllPeople();
        System.out.println(node2.getLength());
        System.out.println("\ngetting all people\n");
        for (int i = 0; i < node2.getLength(); i++) {

            System.out.println(node2.item(i).getTextContent());

       }

        //getting Healthprofile by ID number
        Node node3 = test.getHealthprofile("0005");
        System.out.println("\nthe Healthprofile of person with id 0005\n");
        System.out.println(node3.getTextContent());


       
       //prints people that fulfill a condition
       NodeList node4 = test.getPeopleByWeight("90",">");
       System.out.println("\ngetting people with weight >90\n");
       for (int i = 0; i < node4.getLength(); i++) {

            System.out.println(node4.item(i).getTextContent());

       }

    }
}
