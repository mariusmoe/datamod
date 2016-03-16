package helper;

import javafx.scene.control.TableRow;
import javafx.scene.input.KeyCode;

/**
 * @deprecated
 * @author moe
 *
 */
public class MyEnterStringTableRow extends TableRow<TrainingRow> {

	public void addEventFilter(KeyCode enter, MyEnterEventHandler myEnterEventHandler) {
		// TODO Auto-generated method stub
		
	}
	
	
    @Override
    public void updateItem(TrainingRow item, boolean empty) {
        super.updateItem(item, empty);
        setText(empty ? null : getString());
        setGraphic(null);
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }

    
}
