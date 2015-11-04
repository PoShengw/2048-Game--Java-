package code.interfaces;

import java.util.ArrayList;

import javax.swing.JButton;

public class Button {

	private ArrayList<JButton> _button;

	public Button(int size) {

		_button = new ArrayList<JButton>();
		for (int i = 1; i <= size; i++) {
			_button.add(new JButton());

		}

	}

	public ArrayList<JButton> getbutton() {
		return _button;
	}

}
