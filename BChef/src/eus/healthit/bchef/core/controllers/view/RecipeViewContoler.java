package eus.healthit.bchef.core.controllers.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import eus.healthit.bchef.core.view.panels.center.CenterViewRecipe;

public class RecipeViewContoler extends MouseAdapter {

	CenterViewRecipe viewRecipe;

	public RecipeViewContoler(CenterViewRecipe viewRecipe) {
		this.viewRecipe = viewRecipe;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2 && !e.isConsumed()) {
			e.consume();
			System.out.println("Selecteeeeedddd");
			/*
			 * METELE LA FUNCION QUE TE LLEVE A LA PANTALLA DE USUARIO QUE NO SEAS TU, TIENS QUE LLAMAR A LA FUNCION DE 
			 *  CENTERVIEW Y LE PASAS EL USUARIO QUE SE HAYA SELECCIONADO Y DESDE AHI USAS EL CENTERVIEWCONTROLER Y VUELVES A LLAMAR A LA FUNCION QUE TIENEs
			 *  QUE CREAR
			 */
			//viewRecipe.changeToProfileView();
		}
	}
}
