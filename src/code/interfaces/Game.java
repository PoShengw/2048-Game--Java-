package code.interfaces;

import java.io.IOException;

public class Game {
	Model _m;
	View _v;

	public Game() throws IOException{
		
		_m = new Model();
		_v = new View( _m);
		_v.updatehistoryscore();
		_v.updatehighestscore();
		_v.updatescoreview();
	}
	
	public static void main(String[] args) throws IOException{
		
		new Game();
		
	}
}
