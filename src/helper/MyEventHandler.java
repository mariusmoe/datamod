package helper;

import controllers.GetTrainingController;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.input.MouseEvent;

public class MyEventHandler implements EventHandler<MouseEvent> {
	 
    @Override
    public void handle(MouseEvent t) {
        TableCell c = (TableCell) t.getSource();
        int index = c.getIndex();
        String key = c.getAccessibleText();
        //System.out.println("Index of clicked: "+c + "ooo: "+ key + "lll: "+ index);
        
        //System.out.println(GetTrainingController.list.get(index).getTime());
        System.out.println(GetTrainingController.list.get(index).getTrueTime());
        GetTrainingController gtf = new GetTrainingController();
        gtf.popup(GetTrainingController.list.get(index).getTrueTime());
        /*
        System.out.println("id = " + dataset.get(index).getId());
        System.out.println("name = " + recordList.get(index).getName());
        System.out.println("lastName = " + recordList.get(index).getLastName());
        System.out.println("email = " + recordList.get(index).getEmail());
        */
    }
}