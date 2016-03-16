package helper;

import javafx.scene.control.TableCell;

/**
 * Help create a callback when a cell has been clicked
 * @author Marius Oscar Moe
 */
public class MyIntegerTableCell extends TableCell<TrainingRow, Integer> {
	 
    @Override
    public void updateItem(Integer item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? null : getString());
        setGraphic(null);
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}

