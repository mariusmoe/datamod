package helper;

import javafx.scene.control.TableCell;

public class MyStringTableCell extends TableCell<TrainingRow, String> {

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? null : getString());
        setGraphic(null);
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
