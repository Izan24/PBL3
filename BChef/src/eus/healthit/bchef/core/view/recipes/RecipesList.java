package eus.healthit.bchef.core.view.recipes;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.xml.datatype.DatatypeConstants.Field;

import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class RecipesList extends AbstractListModel<Recipe> {

	List<Recipe> list;

	public RecipesList() {
		list = new ArrayList<>();
		initList();
	}

	private void initList() {
		try {
			List<RecipeStep> steps = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				RecipeStep step = new RecipeStep(RecipeStepActions.OVEN, 2,
						ImageIO.read(new File("resources/recipeIcons/calentarHorno.jpg")), "Esti es ek texto " + i, i);
				step.setId(i);
				Duration duration = Duration.ofSeconds(20);
				duration = duration.plusMinutes(90);
				duration = duration.plusHours(23);
				step.setDuration(duration);
				steps.add(step);
			}

//			list.add(new Recipe(UUID.randomUUID(), "Prueba", "Rkolay", 2, "REceta de prueba woo", 10, null, null, null,
//					steps, ImageIO.read(new File("resources/recipeIcons/recetaBonita.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setList(List<Recipe> list) {
		System.out.println("seteando la lista:");

		for (Recipe r : list) {
			System.out.println(r.toString());
		}
		this.list = list;
	}

	public void addElement(Recipe elemento) {
		if (elemento != null) {
			list.add(elemento);
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	public void deleteElement(Recipe elemento) throws IndexOutOfBoundsException {
		if (elemento != null) {
			list.remove(list.indexOf(elemento));
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	@Override
	public Recipe getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}
}
