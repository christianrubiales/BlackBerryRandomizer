import java.util.Random;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.text.TextFilter;


public class Randomizer extends UiApplication {

	public static void main(String[] args) {
		Randomizer theApp = new Randomizer();
		theApp.enterEventDispatcher();
	}
	
	public Randomizer() {
		pushScreen(new RandomizerScreen());
	}
	
	final class RandomizerScreen extends MainScreen {
		
		public RandomizerScreen() {

			setTitle("Randomizer");
			
			TextField textField = new TextField();
			textField.setLabel("Result : ");
			
			BasicEditField editField = new BasicEditField();
			editField.setLabel("Maximum?: ");
			editField.setMaxSize(3);
			editField.setFilter(TextFilter.get(TextFilter.NUMERIC));
			editField.setChangeListener(new MyFieldChangeListener(textField));
			
			add(editField);
			add(textField);
			
			editField.setFocus();
		}
		
		public boolean onClose() {
			Dialog.inform("(c) Christian Rubiales");
			System.exit(0);
			return true;
		}
		
		final class MyFieldChangeListener implements FieldChangeListener {

			final Random random = new Random();
			final TextField textField;
			
			public MyFieldChangeListener(TextField textField) {
				this.textField = textField;
			}
			
			public void fieldChanged(Field field, int context) {

				String text = ((BasicEditField) field).getText();
				
				if (text != null && text.trim().length() > 0) {
					try {
						// animate possible values
						if (Integer.parseInt(text) > 1) {
							for (int i = 0; i < 20; i++) {
								this.textField.setText(Integer.toString(random.nextInt(Integer.parseInt(text)) + 1));
								doPaint();
								Thread.sleep(25);
							}
						}
						
						// set the final value
						this.textField.setText(Integer.toString(random.nextInt(Integer.parseInt(text)) + 1));
					} catch (Exception e) {
						this.textField.setText("");
					}
				} else {
					this.textField.setText("");
				}
			}
			
		}
	}
}
