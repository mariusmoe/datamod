package helper;

import controllers.GetTrainingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;

/**
 * @deprecated
 * @author moe
 *
 */
public class MyEnterEventHandler implements EventHandler<ActionEvent> {
	 
    @Override
    public void handle(ActionEvent t) {
    	TableCell c = (TableCell) t.getSource();
        int index = c.getIndex();
        String key = c.getAccessibleText();

        System.out.println(GetTrainingController.list.get(index).getTrueTime());
        GetTrainingController gtf = new GetTrainingController();
        String str = gtf.list.get(index).getId();
        gtf.popup(GetTrainingController.list.get(index).getTrueTime(), str);

    }

}