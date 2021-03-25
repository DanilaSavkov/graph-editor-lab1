package file;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;

public class GraphFileWriter implements XMLConstants {
    private static final String FORMAT = ".graph";

    public static void write(Graph<Vertex, Edge> graph) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement(GRAPH);
        root.setAttribute(NAME, graph.getName());
        document.appendChild(root);

        Element vertices = document.createElement(VERTICES);
        Element edges = document.createElement(EDGES);

        for (Vertex vertex : graph.getVertices()) {
            Element vertexElement = document.createElement(VERTEX);
            vertexElement.setAttribute(IDENTIFIER, vertex.getIdentifier());
            vertexElement.setAttribute(CENTER_X, String.valueOf(vertex.getX()));
            vertexElement.setAttribute(CENTER_Y, String.valueOf(vertex.getY()));
            vertices.appendChild(vertexElement);
        }

        for (Edge edge : graph.getEdges()) {
            Element edgeElement = document.createElement(EDGE);
            edgeElement.setAttribute(IDENTIFIER ,edge.getIdentifier());

            Element source = document.createElement(SOURCE);
            source.setAttribute(CENTER_X, String.valueOf(edge.getSource().getX()));
            source.setAttribute(CENTER_Y, String.valueOf(edge.getSource().getY()));

            Element destination = document.createElement(DESTINATION);
            destination.setAttribute(CENTER_X, String.valueOf(edge.getDestination().getX()));
            destination.setAttribute(CENTER_Y, String.valueOf(edge.getDestination().getY()));

            edgeElement.appendChild(source);
            edgeElement.appendChild(destination);

            edges.appendChild(edgeElement);
        }

        root.appendChild(vertices);
        root.appendChild(edges);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        try {
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File(graph.getName() + FORMAT));
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(source, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
