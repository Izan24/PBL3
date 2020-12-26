package eus.healthit.bchef.core.controllers.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectionListener extends MouseAdapter {

	public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            System.out.println("Selecteeeeedddd");
            /*
             * METELE LA FUNCION QUE TE ABRA LA RECETA CUANDO HAYAS HECHO LA VISTA DE RECETA
             */
        }
    }
}
