package handlers;

import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import javafx.scene.shape.Line;
import model.Selectable;
import model.vertecies.GraphicalVertex;
import view.components.Sheet;

import java.util.List;
import java.util.Optional;

public class SheetHandler {
    // обработчик событий нажатий мышью по панели


//    // обработчик событий нажатий клавиш на клавиатуре
//    public static EventHandler<KeyEvent> keyReleased(Sheet sheet) {
//        return new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent keyEvent) {
//                switch (keyEvent.getCode()) {
//                    case DELETE:
//                        deleteAllVertices(sheet);
//                        break;
//                    case I:
//                        setLastVertexIdentifier(sheet);
//                        break;
//                }
//            }
//        };
//    }
//
//    private static void deleteAllVertices(Sheet sheet) {
//        for (Selectable vertex : sheet.getSelectedVertices()) {
//            sheet.remove(vertex);
//        }
//    }
//
//    private static void setLastVertexIdentifier(Sheet sheet) {
//        List<Selectable> selectedVertices = sheet.getSelectedVertices();
//        Selectable vertex = null;
//        if (!selectedVertices.isEmpty()) vertex = selectedVertices.get(selectedVertices.size() - 1);
//        if (vertex != null) {
//            unselectAllVertices(sheet);
//            sheet.select(vertex);
//            String id = getUserIdentifier(vertex);
//            if (id != null) {
//                vertex.setIdentifier(id);
//            }
//        }
//    }
//
//    private static String getUserIdentifier(GraphicalVertex vertex) {
//        TextInputDialog dialog = new TextInputDialog(vertex.getIdentifier());
//        dialog.setTitle("Change identifier");
//        dialog.setHeaderText(null);
//        dialog.setGraphic(null);
//        dialog.setContentText("New identifier:");
//        Optional<String> result = dialog.showAndWait();
//        return result.orElse(null);
//    }
}
