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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphFileWriter implements XMLConstants {
    private static final String FORMAT = ".graph";
    private final Graph<Vertex, Edge> graph;
    private Document document;

    public GraphFileWriter(Graph graph) {
        this.graph = (Graph<Vertex, Edge>) graph;
    }

    public static String getFORMAT() {
        return FORMAT;
    }

    public void writeXML(File directory) throws XMLGenerationException {
        try {
            document = buildDocument();
            configureDocument();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File(directory + FORMAT));
            StreamResult streamResult = new StreamResult(writer);
            transformer.transform(source, streamResult);
        } catch (TransformerException | IOException | ParserConfigurationException e) {
            throw new XMLGenerationException("XML Document can't be generated", e);
        }
    }

    private static Document buildDocument() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }

    private void configureDocument() {
        Element root = getDocumentRoot();
        root.appendChild(getVerticesElement());
        root.appendChild(getEdgesElement());
        document.appendChild(root);
    }

    private Element getVertexElement(Vertex vertex, String name) {
        Element element = document.createElement(name);
        element.setAttribute(IDENTIFIER, vertex.getIdentifier());
        element.setAttribute(CENTER_X, String.valueOf(vertex.getX()));
        element.setAttribute(CENTER_Y, String.valueOf(vertex.getY()));
        return element;
    }

    private Element getVerticesElement() {
        Element vertices = document.createElement(VERTICES);
        for (Vertex vertex : graph.getVertices()) {
            vertices.appendChild(getVertexElement(vertex, VERTEX));
        }
        return vertices;
    }

    private Element getEdgeElement(Edge edge) {
        Element element = document.createElement(EDGE);
        element.setAttribute(IDENTIFIER, String.valueOf(edge.getWeight()));
        element.appendChild(getVertexElement(edge.getSource(), SOURCE));
        element.appendChild(getVertexElement(edge.getDestination(), DESTINATION));
        return element;
    }

    private Element getEdgesElement() {
        Element edges = document.createElement(EDGES);
        for (Edge edge : graph.getEdges()) {
            edges.appendChild(getEdgeElement(edge));
        }
        return edges;
    }

    private Element getDocumentRoot() {
        Element root = document.createElement(GRAPH);
        root.setAttribute(NAME, graph.getName());
        return root;
    }
}
