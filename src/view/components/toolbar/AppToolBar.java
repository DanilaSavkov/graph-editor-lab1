package view.components.toolbar;

import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import model.edges.GraphicalEdge;
import model.vertecies.GraphicalVertex;
import view.components.tabpane.Sheet;

import java.util.List;

public class AppToolBar extends ToolBar {
    private static final VertexButton VERTEX_BUTTON = new VertexButton();
    private static final EdgeButton EDGE_BUTTON = new EdgeButton();
    private static final Separator SEPARATOR = new Separator();

    private Sheet sheet;

    public AppToolBar(Sheet sheet) {
        super(VERTEX_BUTTON, SEPARATOR, EDGE_BUTTON);
        this.setOrientation(Orientation.VERTICAL);
        this.sheet = sheet;
        VERTEX_BUTTON.setOnAction(this::vertexButtonOnAction);
        EDGE_BUTTON.setOnAction(this::edgeButtonOnAction);
        SEPARATOR.setMaxWidth(ToolButton.getButtonSideSize());
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setDisable() {
        EDGE_BUTTON.setUnselected();
        VERTEX_BUTTON.setUnselected();
    }

    public void vertexButtonOnAction(ActionEvent actionEvent) {
        VERTEX_BUTTON.setSelected();
        EDGE_BUTTON.setUnselected();
        setVertexCreationMode();
    }

    public void edgeButtonOnAction(ActionEvent actionEvent) {
        VERTEX_BUTTON.setUnselected();
        EDGE_BUTTON.setSelected();
        setEdgeCreationMode();
    }

    private void setVertexCreationMode() {
        sheet.setHandlers(sheet.mouseClickedHandler());
        for (GraphicalVertex vertex : sheet.getGraph().getVertices()) {
            vertex.setCircleHandlers(vertex.mouseClickedHandler(), vertex.mouseDraggedHandler(), vertex.mouseEnteredHandler(), vertex.mouseExitedHandler());
        }
    }

    private void setEdgeCreationMode() {
        buildNewEdge();
        sheet.unselectAll();
        sheet.setHandlers(null);
        for (GraphicalVertex vertex : sheet.getGraph().getVertices()) {
            vertex.setCircleHandlers(null, null, vertex.mouseEnteredHandler(), vertex.mouseExitedHandler());
        }
        for (GraphicalEdge edge : sheet.getGraph().getEdges()) {
            edge.setLineHandlers(null, edge.mouseEnteredHandler(), edge.mouseExitedHandler());
        }
    }

    private void buildNewEdge() {
        List<GraphicalVertex> selectedVertices = sheet.getSelectedVertices();
        if (selectedVertices.size() > 1) {
            GraphicalVertex source = selectedVertices.get(selectedVertices.size() - 1);
            GraphicalVertex destination = selectedVertices.get(selectedVertices.size() - 2);
            GraphicalEdge edge = new GraphicalEdge(source, destination);
            sheet.add(edge);
        }
    }
}
