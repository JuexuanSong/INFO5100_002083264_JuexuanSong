import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLJSONParser {
    public static void main(String[] args) {
        try {
            // XML Parsing
            File xmlFile = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();
            
            System.out.println("Original XML Data:");
            printXML(document);
            
            // Add new book to XML
            addBookToXML(document);
            
            System.out.println("Updated XML Data:");
            printXML(document);
            
            // JSON Parsing
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("books.json");
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            
            System.out.println("Original JSON Data:");
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
            
            // Add new book to JSON
            addBookToJSON(rootNode, objectMapper, jsonFile);
            
            System.out.println("Updated JSON Data:");
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printXML(Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }

    private static void addBookToXML(Document document) {
        Element root = document.getDocumentElement();
        Element newBook = document.createElement("Book");

        Element title = document.createElement("title");
        title.appendChild(document.createTextNode("New Java Book"));
        newBook.appendChild(title);

        Element publishedYear = document.createElement("publishedYear");
        publishedYear.appendChild(document.createTextNode("2025"));
        newBook.appendChild(publishedYear);

        Element numberOfPages = document.createElement("numberOfPages");
        numberOfPages.appendChild(document.createTextNode("350"));
        newBook.appendChild(numberOfPages);

        Element authors = document.createElement("authors");
        Element author = document.createElement("author");
        author.appendChild(document.createTextNode("John Doe"));
        authors.appendChild(author);
        newBook.appendChild(authors);

        root.appendChild(newBook);
    }

    private static void addBookToJSON(JsonNode rootNode, ObjectMapper objectMapper, File jsonFile) throws IOException {
        if (rootNode.isArray()) {
            ArrayNode arrayNode = (ArrayNode) rootNode;
            ObjectNode newBook = objectMapper.createObjectNode();
            newBook.put("title", "New Java Book");
            newBook.put("publishedYear", 2025);
            newBook.put("numberOfPages", 350);
            
            ArrayNode authorsNode = objectMapper.createArrayNode();
            authorsNode.add("John Doe");
            newBook.set("authors", authorsNode);
            
            arrayNode.add(newBook);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, rootNode);
        }
    }
}
