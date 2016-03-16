package helper;

import controllers.GetTrainingController;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

/**
 * Help create a callback when a cell has been clicked
 * @author Marius Oscar Moe
 */
public class MyEventHandler implements EventHandler<MouseEvent> {
	 
    @Override
    public void handle(MouseEvent t) {
        TableCell c = (TableCell) t.getSource();
        int index = c.getIndex();
        String key = c.getAccessibleText();
        
        //System.out.println(GetTrainingController.list.get(index).getTime());
        //System.out.println(GetTrainingController.list.get(index).getTrueTime());
        GetTrainingController gtf = new GetTrainingController();
        String str = gtf.list.get(index).getId();
        gtf.popup(GetTrainingController.list.get(index).getTrueTime(), str);

    }
}