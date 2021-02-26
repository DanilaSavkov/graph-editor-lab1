package handlers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.GraphicalVertex;
import model.Vertex;
import view.components.Sheet;

public class SheetHandler {
    public static EventHandler<MouseEvent> clickEvent(Sheet sheet) {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                    unselectAll(sheet);
                    if (mouseEvent.getClickCount() == 2) {
                        GraphicalVertex graphicalVertex = getVertexByClick(mouseEvent);
                        if (!sheet.contains(graphicalVertex)) {
                            sheet.addVertex(graphicalVertex);
                        }
                    }
                }
            }
        };
    }

    private static GraphicalVertex getVertexByClick(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        return new GraphicalVertex(x, y);
    }

    private static void unselectAll(Sheet sheet) {
        for (Vertex vertex : sheet.getGraph().getVertices()) {
            ((GraphicalVertex) vertex).setUnselected();
        }
    }
}
