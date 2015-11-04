package code.interfaces;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MyKeyListener implements KeyListener {
	
	private Model _model;
	public MyKeyListener(Model m){
		
		_model = m;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			_model.getright(true);
			_model.getleft(false);
			_model.getup(false);
			_model.getdown(false);
			System.out.println("right click");
			_model.shifting();
			try {
				_model.combine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		if(e.getKeyCode()== KeyEvent.VK_LEFT){
			_model.getright(false);
			_model.getleft(true);
			_model.getup(false);
			_model.getdown(false);
			
			System.out.println("left click");
			_model.shifting();
			try {
				_model.combine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getKeyCode()== KeyEvent.VK_UP){
			_model.getright(false);
			_model.getleft(false);
			_model.getup(true);
			_model.getdown(false);
			System.out.println("up click");
			_model.shifting();
			try {
				_model.combine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if(e.getKeyCode()== KeyEvent.VK_DOWN){
			_model.getright(false);
			_model.getleft(false);
			_model.getup(false);
			_model.getdown(true);
			System.out.println("down click");
			_model.shifting();
			try {
				_model.combine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
