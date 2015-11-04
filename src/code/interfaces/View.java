package code.interfaces;
// http://www.cnblogs.com/youxin/archive/2012/11/16/2772540.html   GUI Layout  
// http://blog.yam.com/run26/article/6858475  Jump out window
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.util.ArrayList;

import javax.swing.JButton;

public class View {

	private JFrame _window;
	private JPanel _panel;
	
	private Button _button;
	private Container _container;
	private Model _model;
	private int _size;
	private JPanel _scorepanel;
	private JButton _scorebutton;
	private JButton _highestscorebutton;
    private JButton _restartbutton;
    private JButton _historyscorebutton;
    private JPanel _leftpanel;
	private JLabel _label;
	private JLabel _labeldescribe;
    
	//////////// Constructor///////////////
	public View(Model m) throws IOException {
		_model = m;
		_model.addObserver(this);
        _size = _model.getboxsize();
		
         /////  GUI set up  ////
        _window = new JFrame();
        _window.pack();
   	    _window.setExtendedState(JFrame.MAXIMIZED_BOTH);   // Get frame size 
   	    
   	    // Get the size of screen
   	    
   	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      double width = screenSize.getWidth();
   	  double height = screenSize.getHeight();
   	  System.out.println("width is" + width);
   	  System.out.println("height is" + height);
 	  
      int w = (int) width;
   	  int h = (int) height;
   	     	 
   	    
   	    // Get the size of screen 
   	    	    
   	    _window.setBackground(Color.BLACK);
//		_window.setSize(1100, 1000);
//		_window.setLocation(400, 400);
		
        _window.setVisible(true);
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 _window.setResizable(true);
        _panel = new JPanel();
        _panel.setLayout(new GridLayout(_size, _size));
        _panel.setBackground(Color.BLACK);
     
        //_panel.setBounds(360, 0, 700 , 700 );     // 4x4 grid///
        _panel.setBounds((int)(w/3.8),0,(int)(w/1.95), (int)(w/1.95) );
        _container = _window.getContentPane();
        _container.setLayout(null);
        _container.add(_panel);
        
        
        
        _leftpanel = new JPanel();
        _leftpanel.setBackground(Color.BLACK);
        _container.add(_leftpanel);
        _leftpanel.setBounds(0, 0, 350, 700);
       // _leftpanel.setLayout(new GridLayout(2,1));
        _leftpanel.setLayout(null);
        
        ImageIcon image = new ImageIcon("Capture.JPG");
        _label = new JLabel( image);
        _label.setBounds(5, 0, 350, 175);
        _leftpanel.add(_label);
        
        // _labeldescribe = new JLabel("Join the numbers and get to the 2048 tile!! ",SwingConstants.CENTER);
        _labeldescribe = new JLabel(); 
         _labeldescribe.setBounds(5, 400, 350, 175);
         _labeldescribe.setForeground(Color.WHITE);
         _labeldescribe.setFont(new Font("Cooper Black",Font.PLAIN,40));
         
         _labeldescribe.setText("       "+"Let's GO !!");
       // _highestscorebutton.setText("<html> Best Score " + "<br>" +  _model.gethighestscore() + "</html>");
         
         _leftpanel.add(_labeldescribe); 
        
         _restartbutton = new JButton();
         _leftpanel.add(_restartbutton);
         
         _restartbutton.setBounds(5,523,335,175);
         _restartbutton.setBackground(Color.BLACK);
         _restartbutton.setForeground(Color.WHITE);
         _restartbutton.setFont(new Font("Cooper Black",Font.PLAIN,35));
         _restartbutton.setText(" New Game"); 
     
         
         
         
	//	_panel.setBackground(Color.yellow);
    //   _container.setLayout(new GridBagLayout());
	//	_panel.setPreferredSize(new Dimension(700,700));
     
		
		 
	    // Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
		//System.out.println("screen size is  "+ displaySize);
	    //_window.setSize(displaySize.width,displaySize.height);
        //_window.setSize(300, 400);
     	//_window.add(_panel,BorderLayout.WEST);
		
				
		_button = new Button(_size*_size);
       	for (int i = 0; i <= (_size*_size)-1; i++) {
			_button.getbutton().get(i)
					.addKeyListener(new MyKeyListener(_model));
			_panel.add(_button.getbutton().get(i));
			_button.getbutton().get(i).setFont(new Font("AR DARLING",Font.PLAIN,50));
			_button.getbutton().get(i).setForeground(new Color(255,185,15));
			_button.getbutton().get(i).setBackground(Color.BLACK);
		}

		_model.randompoint();
		
		
		_scorepanel = new JPanel();
		_scorepanel.setLayout(null);
		_container.add(_scorepanel);
		_scorepanel.setBounds(1070, 0, 300, 700);
		_scorepanel.setBackground(Color.BLACK);
		
		_scorebutton = new JButton();
		
		_highestscorebutton = new JButton();
		_historyscorebutton = new JButton();
		
		_scorepanel.add(_scorebutton);
		_scorepanel.add(_highestscorebutton);
		//_scorepanel.add(_restartbutton);
		_scorepanel.add(_historyscorebutton);
		
		_historyscorebutton.setBounds(3, 3, 290, 175);
		_historyscorebutton.setBackground(Color.BLACK);
		_historyscorebutton.setForeground(Color.WHITE);
		_historyscorebutton.setFont(new Font("Cooper Black",Font.PLAIN,35));
		
		_highestscorebutton.setBounds(3, 180, 290, 175);
		_highestscorebutton.setBackground(Color.BLACK);
		_highestscorebutton.setForeground(Color.WHITE);
		_highestscorebutton.setFont(new Font("Cooper Black",Font.PLAIN,35));
		
		_scorebutton.setBounds(3, 358, 290, 340);
		_scorebutton.setBackground(Color.BLACK);
		_scorebutton.setForeground(new Color(192,192,192));
		_scorebutton.setFont(new Font("Cooper Black",Font.PLAIN,70));
		
		_restartbutton.addActionListener(new MyActionListener(_model));
		
		
		
	}

	// ////////////// Method ////////////////////////

	public void updateview() {
		System.out.println("enter updateview");
		for(int i=0;i<=(_size*_size)-1;i++){
			//System.out.println("enter update for loop");
		  if(_model.getempty().contains(_model.getpoint().get(i)) == false){
		  _button.getbutton().get(i).setText("" + _model.getmap().get(_model.getpoint().get(i)));
		   
		   //_button.getbutton().get(i).setForeground(new Color(192,192,192));
		    
		  } 
		  else{
			  _button.getbutton().get(i).setText("");
		  }
		  
		}
	}
	
	
	public void updatescoreview(){
		_scorebutton.setText("" + _model.getscore());
		
	}
	
	
	
	public void updatehighestscore(){
		//_highestscorebutton.setText("Best score" + _model.gethighestscore());
		_highestscorebutton.setText("<html> Best Score " + "<br>" +  _model.gethighestscore() + "</html>");
	}
	
	
	public void updatehistoryscore(){
		//_historyscorebutton.setText("History " + "\n" + _model.gethistoryscore());
		_historyscorebutton.setText("<html> History " + "<br>" +  _model.gethistoryscore() + "</html>");

	}
}
