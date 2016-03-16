package helper;

import javafx.scene.control.TableCell;

/**
 * Help create a callback when a cell has been clicked
 * @author Marius Oscar Moe
 */
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
