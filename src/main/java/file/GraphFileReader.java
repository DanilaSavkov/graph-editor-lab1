package file;

import model.edges.Edge;
import model.graphs.Graph;
import model.vertecies.Vertex;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class GraphFileReader implements XMLConstants {
    private static Graph<Vertex, Edge> graph;

    public static class XMLGraphReader extends DefaultHandler {
        private String identifier;
        private Vertex source;
        private Vertex destination;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            switch (qName) {
                case VERTEX:
                    String identifier = attributes.getValue(IDENTIFIER);
                    double centerX = Double.parseDouble(attributes.getValue(CENTER_X));
                    double centerY = Double.parseDouble(attributes.getValue(CENTER_Y));
                    Vertex vertex = new Vertex(centerX, centerY);
                    vertex.setIdentifier(identifier);
                    graph.add(vertex);
                    break;
                case SOURCE:
                    centerX = Double.parseDouble(attributes.getValue(CENTER_X));
                    centerY = Double.parseDouble(attributes.getValue(CENTER_Y));
                    this.source = graph.find(new Vertex(centerX, centerY));
                    break;
                case DESTINATION:
                    centerX = Double.parseDouble(attributes.getValue(CENTER_X));
                    centerY = Double.parseDouble(attributes.getValue(CENTER_Y));
                    this.destination = graph.find(new Vertex(centerX, centerY));
                    break;
                case EDGE:
                    this.identifier = attributes.getValue(IDENTIFIER);
                    break;
                case GRAPH:
                    String name = attributes.getValue(NAME);
                    graph = new Graph<>(name);
                    break;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals(EDGE)) {
                Edge edge = new Edge(source, destination);
                edge.setWeight(Integer.valueOf(identifier));
                graph.add(edge);
            }
        }
    }

    public static Graph<Vertex, Edge> read(File file) throws XMLReadingException {
        parseFile(file);
        return graph;
    }

    private static void parseFile(File file) throws XMLReadingException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLGraphReader handler = new XMLGraphReader();
            parser.parse(file, handler);
        } catch (ParserConfigurationException | IOException | SAXException exception) {
            throw new XMLReadingException("File can't be read...", exception);
        }
    }
}