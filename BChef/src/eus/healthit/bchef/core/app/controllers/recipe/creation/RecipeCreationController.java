package eus.healthit.bchef.core.app.controllers.recipe.creation;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.app.ui.dialogs.FileChooser;
import eus.healthit.bchef.core.app.ui.panels.center.CenterPreviewRecipe;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewCreateRecipe;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;

public class RecipeCreationController implements IRoundButtonListener, ActionListener, KeyListener {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	String TITLE_DEFAULT_TEXT = rb.getString("title_text");
	String DESCRIPTION_DEFAULT_TEXT = rb.getString("description_text");
	String INGREDIENT_DEFAULT_TEXT = rb.getString("ingredient_text");
	String QUANTITY_DEFAULT_TEXT = rb.getString("quantity_text");
	String STEP_DEFAULT_TEXT = rb.getString("step_text");

	JFrame framePreview;
	CenterViewCreateRecipe createRecipeView;
	WindowFrame window;
	CenterViewController centerController;
	User user;

	String oldIngredientName;
	List<Ingredient> suggestionIngredientList;

	public RecipeCreationController(CenterViewCreateRecipe createRecipeView, WindowFrame window, User user,
			CenterViewController centerController) {
		this.createRecipeView = createRecipeView;
		this.window = window;
		this.user = user;
		this.centerController = centerController;

		oldIngredientName = "";

		suggestionIngredientList = new ArrayList<>();

		createPreviewWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!createRecipeView.getIngredientName().equals(oldIngredientName)) {

			suggestionIngredientList = JSONCalls.ingredientLike(createRecipeView.getIngredientName());

			createRecipeView.setAutoCompleteList(suggestionIngredientList);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case RecipeCreationControllerAC.ACTION_CHANGE:
			createRecipeView.changeValueLimits();
			break;

		default:
			break;
		}
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {

		case RecipeCreationControllerAC.ADD_IMAGE:
			FileChooser file = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				file = new FileChooser();
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				Image img = ImageIO.read(file.getSelectedFile());
				createRecipeView.setImage(img);
				createRecipeView.setImagePath(file.getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case RecipeCreationControllerAC.ADD_IMAGE_STEP:
			FileChooser fileImage = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				fileImage = new FileChooser();
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				Image img = ImageIO.read(fileImage.getSelectedFile());
				createRecipeView.setStepImage(img);
				createRecipeView.setStepImagePath(fileImage.getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
			}

			break;

		case RecipeCreationControllerAC.ADD_INGREDIENT:

			if (checkIngredient()) {
				Ingredient ingredient = ingredientExists();
				if (ingredient == null) {
					new CreationErrorDialog(window, rb.getString("invalid_ingredient_title"), true,
							rb.getString("invalid_ingredient_text"));
				} else {
					ingredient.setQuantity(createRecipeView.getIngredientQuantity());
					addIngredient(ingredient);
					createRecipeView.resetIngredientFields();
				}
			}
			break;

		case RecipeCreationControllerAC.REMOVE_INGREDIENT:
			createRecipeView.removeIngredient();
			break;

		case RecipeCreationControllerAC.ADD_STEP:
			if (checkStep()) {
				addStep();
				createRecipeView.resetStepFIelds();
			}
			break;

		case RecipeCreationControllerAC.REMOVE_STEP:
			createRecipeView.removeStep();
			break;

		case RecipeCreationControllerAC.CREATE_RECIPE:
			if (recipeValid()) {
				addStepNumbers();
				Recipe recipe = new Recipe(UUID.randomUUID(), createRecipeView.getTitle(), createRecipeView.getAuthor(),
						user.getId(), createRecipeView.getDescription(),
						createRecipeView.getIngredientListModel().getList(),
						createRecipeView.getStepListModel().getList(), createRecipeView.getImagePath());
				recipe.setImage(createRecipeView.getImage());

				JSONCalls.addRecipe(recipe);
				user.addPublication(recipe);

				new CreationErrorDialog(window, rb.getString("recipe_created_title"), true,
						rb.getString("recipe_created_text"));
				createRecipeView.resetAllFields();
				centerController.setListView();

			}
			break;

		case RecipeCreationControllerAC.PREVIEW:
			openPreviewWindow();
			break;
		}
	}

	private void addStepNumbers() {
		List<RecipeStep> ls = createRecipeView.getStepListModel().getList();
		for (int i = 0; i < ls.size(); i++) {
			ls.get(i).setNum(i + 1);
		}
	}

	private void addIngredient(Ingredient ingredient) {
		createRecipeView.getIngredientListModel().addElement(ingredient);
	}

	private boolean recipeValid() {
		if (createRecipeView.getTitle().trim().equals("") || createRecipeView.getTitle().equals(TITLE_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_title_title"), true,
					rb.getString("invalid_title_text"));
			return false;
		} else if (createRecipeView.getDescription().trim().equals("")
				|| createRecipeView.getDescription().equals(DESCRIPTION_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_description_title"), true,
					rb.getString("invalid_description_text"));
			return false;
		} else if (createRecipeView.getIngredientListModel().getSize() == 0) {
			new CreationErrorDialog(window, rb.getString("invalid_ingredient_title"), true,
					rb.getString("invalid_ingredient_missing_text"));
			return false;
		} else if (createRecipeView.getStepListModel().getSize() == 0) {
			new CreationErrorDialog(window, rb.getString("invalid_step_title"), true,
					rb.getString("invalid_step_text"));
			return false;
		}
		return true;
	}

	private void openPreviewWindow() {
		CenterPreviewRecipe preview = new CenterPreviewRecipe();
		preview.setRecipe(createRecipe());

		framePreview.setContentPane(preview);
		framePreview.setSize(1250, 750);
		framePreview.setLocation(400, 75);
		framePreview.setVisible(true);
	}

	public Recipe createRecipe() {
		String title;
		String description;

		if (createRecipeView.getTitle().equals(TITLE_DEFAULT_TEXT)) {
			title = "";
		} else {
			title = createRecipeView.getTitle();
		}

		if (createRecipeView.getDescription().equals(DESCRIPTION_DEFAULT_TEXT)) {
			description = "";
		} else {
			description = createRecipeView.getName();
		}

		Recipe recipe = new Recipe(title, createRecipeView.getAuthor(), 2, description, 5,
				createRecipeView.getIngredients(), createRecipeView.getSteps(), createRecipeView.getImage());

		return (recipe);
	}

	private void createPreviewWindow() {
		framePreview = new JFrame(rb.getString("recipe_preview_text"));
		framePreview.setSize(1250, 750);
		framePreview.setLocation(400, 75);
		framePreview.setResizable(true);
		framePreview.setBackground(Color.white);

		framePreview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private boolean checkIngredient() {
		if (createRecipeView.getIngredientName().trim().equals("")
				|| createRecipeView.getIngredientName().equals(INGREDIENT_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_ingredient_empty_title"), true,
					rb.getString("invalid_ingredient_empty_text"));
			return false;
		} else if (createRecipeView.getIngredientQuantity().trim().equals("")
				|| createRecipeView.getIngredientQuantity().equals(QUANTITY_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_quantity_title"), true,
					rb.getString("invalid_quantity_text"));
			return false;
		}
		return true;
	}

	private Ingredient ingredientExists() {
		if (suggestionIngredientList == null) {
			return null;
		}

		for (Ingredient i : suggestionIngredientList) {
			if (i.getName().toUpperCase().equals(createRecipeView.getIngredientName().toUpperCase())) {
				return i;
			}
		}
		return null;
	}

	public void addStep() {
		RecipeStep step = null;

		step = new RecipeStep(createRecipeView.getAction(), createRecipeView.getValue(),
				createRecipeView.getStepImage(), createRecipeView.getInstruction(), createRecipeView.getValue(),
				parseDuration());

		step.setImagePath(createRecipeView.getStepImagePath());

		createRecipeView.getStepListModel().addElement(step);

		createRecipeView.setStepImage(null);
		createRecipeView.setStepImagePath(null);
	}

	public boolean checkStep() {
		if (createRecipeView.getInstruction().trim().equals("")
				|| createRecipeView.getInstruction().equals(STEP_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_instruction_title"), true,
					rb.getString("invalid_instruction_text"));
			return false;
		} else {
			if (createRecipeView.getAction().equals(RecipeStepActions.TIMER)) {
				Duration duration = parseDuration();
				if (duration.toNanos() == 0) {
					new CreationErrorDialog(window, rb.getString("invalid_time_title"), true,
							rb.getString("invalid_time_text"));
					return false;
				}
			} else if (createRecipeView.getAction().equals(RecipeStepActions.OVEN)
					|| createRecipeView.getAction().equals(RecipeStepActions.STOVE)) {
				if (createRecipeView.getValue() == 0) {
					new CreationErrorDialog(window, rb.getString("invalid_value_title"), true,
							rb.getString("invalid_value_text"));
					return false;
				}
			}
		}
		return true;
	}

	private Duration parseDuration() {
		String fullDuration = createRecipeView.getTime();

		String[] params = fullDuration.split(" ");
		String[] time = params[3].split(":");

		try {
			time[0] = String.valueOf(Integer.parseInt(time[0]));
		} catch (NumberFormatException e) {
		}
		try {
			time[1] = String.valueOf(Integer.parseInt(time[1]));
		} catch (NumberFormatException e) {
		}
		try {
			time[2] = String.valueOf(Integer.parseInt(time[2]));
		} catch (NumberFormatException e) {
		}

		Duration duration = Duration.ofSeconds(Long.valueOf(time[2]));
		duration = duration.plusMinutes(Long.valueOf(time[1]));
		duration = duration.plusHours(Long.valueOf(time[0]));

		return duration;
	}

	public void removeIngredient(Ingredient ingredient) {
		createRecipeView.getIngredientListModel().deleteElement(ingredient);
	}

	public void removeStep(RecipeStep step) {
		createRecipeView.getStepListModel().deleteElement(step);
	}
}
