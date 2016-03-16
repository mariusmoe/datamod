package helper;

import controllers.GetTrainingController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;


public class MyEnterEventHandler implements EventHandler<ActionEvent> {
	 
    @Override
    public void handle(ActionEvent t) {
    	TableCell c = (TableCell) t.getSource();
        int index = c.getIndex();
        String key = c.getAccessibleText();
        //System.out.println("Index of clicked: "+c + "ooo: "+ key + "lll: "+ index);
        
        //System.out.println(GetTrainingController.list.get(index).getTime());
        System.out.println(GetTrainingController.list.get(index).getTrueTime());
        GetTrainingController gtf = new GetTrainingController();
        String str = gtf.list.get(index).getId();
        gtf.popup(GetTrainingController.list.get(index).getTrueTime(), str);
        /*
        System.out.println("id = " + dataset.get(index).getId());
        System.out.println("name = " + recordList.get(index).getName());
        System.out.println("lastName = " + recordList.get(index).getLastName());
        System.out.println("email = " + recordList.get(index).getEmail());
        */
    }

}