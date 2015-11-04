package code.interfaces;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Model {

	private int _score;
	private HashMap<Point, Integer> _map; // put, get contain
	private ArrayList<Point> _empty; // add, remove, get,size collection.shuffle
	private View _view;
	private int BoxSize = 4;
	private boolean Right;
	private boolean Left;
	private boolean Up;
	private boolean Down;
	private ArrayList<Point> _point;
	private int _HighestScore;
	private int _FileHighestScore;
	private boolean _change = true;;

	// ////// Constructor/////////
	public Model() throws IOException {

		_point = new ArrayList<Point>();

		for (int i = 1; i <= BoxSize; i++) {
			for (int j = 1; j <= BoxSize; j++) {
				_point.add(new Point(i, j));
			}
		}

		Right = false;
		Left = false;
		Up = false;
		Down = false;

		_map = new HashMap<Point, Integer>();
		_empty = new ArrayList<Point>();

		for (int i = 0; i <= (BoxSize * BoxSize) - 1; i++) {

			_empty.add(_point.get(i));
		}

		
		BufferedReader br = new BufferedReader(new FileReader("HighestScore.txt"));
		String line;
		line = br.readLine();
		_FileHighestScore = Integer.valueOf(line);
		System.out.println("high is" + _FileHighestScore);
		
	}

	// //////// Method /////////
	public void reset() throws IOException {
		HashMap<Point, Integer> temp_map = new HashMap<Point, Integer>();
		ArrayList<Point> temp_empty = new ArrayList<Point>();
		for (int i = 0; i <= (BoxSize * BoxSize) - 1; i++) {
			temp_empty.add(_point.get(i));
		}
		_empty = temp_empty;
		_map = temp_map;
		_score = 0;
		_view.updatescoreview();
		_change = true;
		this.randompoint();

	}
	
	public int gethistoryscore(){
		return _FileHighestScore;
	}

	public int gethighestscore() {
		return _HighestScore;
	}

	public int getboxsize() {
		return BoxSize;
	}

	public void getright(boolean n) {
		Right = n;
	}

	public void getleft(boolean n) {
		Left = n;
	}

	public void getup(boolean n) {
		Up = n;
	}

	public void getdown(boolean n) {
		Down = n;
	}

	public void addObserver(View v) {
		_view = v;
		
	}

	public void randompoint() throws IOException {
		if(_change==true){
		System.out.println("enter randompoint");
		Collections.shuffle(_empty);

		System.out.println("map is " + _map);

		_map.put(_empty.get(0), 2);
		System.out.println(_empty);

		_empty.remove(0);
		System.out.println(_empty);
		_view.updateview();

		this.judgegameover();
		}
		else{
			
		}
		_change = false;
    }

	// /////////////////////////////////////////////////////////////
	public void shifting() {
		/**
		 * RIGHT ===========================
		 */
		if (Right == true) {
			int iteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = iteration - 1; i >= 1; i--) {
					for (int j = iteration; j > i; j--) {

						if (_map.containsKey(new Point(r, i))
								&& _empty.contains(new Point(r, j))) {
							_map.put(new Point(r, j), _map.get(new Point(r, i)));
							_empty.remove(new Point(r, j));
							_map.remove(new Point(r, i));
							_empty.add(new Point(r, i));
							_change=true;
						}
						
					}

				}
			}
			
		}

		/**
		 * LEFT ===========================
		 */
		 if (Left == true) {

			int iteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = 2; i <= iteration; i++) {
					for (int j = 1; j < i; j++) {

						if (_map.containsKey(new Point(r, i))
								&& _empty.contains(new Point(r, j))) {
							_map.put(new Point(r, j), _map.get(new Point(r, i)));
							_empty.remove(new Point(r, j));
							_map.remove(new Point(r, i));
							_empty.add(new Point(r, i));
							_change=true;
						}
					}

				}

			}
			
		}

		/**
		 * Up ===========================
		 */

			if (Up == true) {
			System.out.println("Indeed enter up");
			int iteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = 2; i <= iteration; i++) {
					for (int j = 1; j < i; j++) {

						if (_map.containsKey(new Point(i, r))
								&& _empty.contains(new Point(j, r))) {
							_map.put(new Point(j, r), _map.get(new Point(i, r)));
							_empty.remove(new Point(j, r));
							_map.remove(new Point(i, r));
							_empty.add(new Point(i, r));
							_change=true;
						}
					}

				}

			}
			
		}

		 if (Down == true) {

			int iteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = iteration - 1; i >= 1; i--) {
					for (int j = iteration; j > i; j--) {

						if (_map.containsKey(new Point(i, r))
								&& _empty.contains(new Point(j, r))) {
							_map.put(new Point(j, r), _map.get(new Point(i, r)));
							_empty.remove(new Point(j, r));
							_map.remove(new Point(i, r));
							_empty.add(new Point(i, r));
							_change=true;
						}
					}

				}
			}
			
		}
		
		
		
		//_view.updateview();
	}

	public void combine() throws IOException {
		/**
		 * Right ===========================
		 */
		if (Right == true) {
			int tempscore = 0;
			int coniteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = coniteration; i >= 2; i--) {
					if (_map.containsKey(new Point(r, i))
							&& _map.get(new Point(r, i)).equals(_map.get(new Point(
									r, i - 1))) ) {
						_map.put(new Point(r, i), _map.get(new Point(r, i)) * 2);
						_map.remove(new Point(r, i - 1));
						_empty.add(new Point(r, i - 1));
						tempscore = tempscore + _map.get(new Point(r, i));
						this.shifting();
					}

				}
			}
			_score = _score + tempscore;
			_view.updateview();
			_view.updatescoreview();
			this.randompoint();

		}

		/**
		 * Left ===========================
		 */
		if (Left == true) {
			int tempscore = 0;
			int coniteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = 1; i <= coniteration - 1; i++) {
					if (_map.containsKey(new Point(r, i))
							&& _map.get(new Point(r, i)).equals(_map.get(new Point(
									r, i + 1)))) {
						_map.put(new Point(r, i), _map.get(new Point(r, i)) * 2);
						_map.remove(new Point(r, i + 1));
						_empty.add(new Point(r, i + 1));
						tempscore = tempscore + _map.get(new Point(r, i));
						this.shifting();
					}

				}
			}
			_score = _score + tempscore;
			_view.updateview();
			_view.updatescoreview();
			this.randompoint();

		}

		/**
		 * Up ===========================
		 */

		if (Up == true) {
			int tempscore = 0;
			int coniteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = 1; i <= coniteration - 1; i++) {
					if (_map.containsKey(new Point(i, r))
							&& _map.get(new Point(i, r)).equals(_map.get(new Point(
									i + 1, r)))) {
						_map.put(new Point(i, r), _map.get(new Point(i, r)) * 2);
						_map.remove(new Point(i + 1, r));
						_empty.add(new Point(i + 1, r));
						tempscore = tempscore + _map.get(new Point(i, r));
						this.shifting();
					}

				}
			}
			_score = _score + tempscore;
			_view.updateview();
			_view.updatescoreview();
			this.randompoint();
		}

		/**
		 * Down ===========================
		 */

		if (Down == true) {
			int tempscore = 0;
			int coniteration = BoxSize;
			for (int r = 1; r <= BoxSize; r++) {
				for (int i = coniteration; i >= 2; i--) {
					if (_map.containsKey(new Point(i, r))
							&& _map.get(new Point(i, r)).equals(_map.get(new Point(
									i - 1, r)))) {
						_map.put(new Point(i, r), _map.get(new Point(i, r)) * 2);
						_map.remove(new Point(i - 1, r));
						_empty.add(new Point(i - 1, r));
						tempscore = tempscore + _map.get(new Point(i, r));
						this.shifting();
					}

				}
			}
			_score = _score + tempscore;
			_view.updateview();
			_view.updatescoreview();
			this.randompoint();

		}

	}

	public ArrayList<Point> getempty() {
		return _empty;
	}

	// // Get button ///////
	public ArrayList<Point> getpoint() {
		return _point;
	}

	// ///// Get map value ////

	public HashMap<Point, Integer> getmap() {
		return _map;
	}

	public int getscore() {

		return _score;
	}

	public void judgegameover() throws IOException {
		System.out.println("enter judge gameover");
		int judge = 0;
		if (_empty.size() == 0) {
			for (int i = 1; i <= BoxSize - 1; i++) {
				for (int j = 1; j <= BoxSize; j++) {
					if (_map.get(new Point(i, j)) == _map.get(new Point(i + 1,
							j))) {
						judge = judge;
					} else {
						judge = judge + 1;
					}
				}
			}

			for (int j = 1; j <= BoxSize - 1; j++) {
				for (int i = 1; i <= BoxSize; i++) {
					if (_map.get(new Point(i, j)) == _map.get(new Point(i,
							j + 1))) {
						judge = judge;
					} else {
						judge = judge + 1;
					}
				}
			}

			if (judge == (BoxSize - 1) * BoxSize * 2) {
				// System.out.println("Game over");
				// _view.updagegamveover();
				if (_score > _HighestScore) {
					_HighestScore = _score;
				} else {
					_HighestScore = _HighestScore;
				}
				JOptionPane.showMessageDialog(null, "Your Scored" + " "
						+ _score + "\n" + "Best score" + " " + _HighestScore,
						"Game Over", JOptionPane.INFORMATION_MESSAGE);
				_view.updatehighestscore();
				
				/// save the highest score into file
				 this.writeintofile();

			}

		}
	}
	
	   public void writeintofile() throws IOException{
		  
		   if(_HighestScore>_FileHighestScore){
		   BufferedWriter bw = new BufferedWriter(new FileWriter("HighestScore.txt"));
		   String s = ""+ _HighestScore;
		   bw.write(s,0,s.length());
		   bw.close();
		   _FileHighestScore = _HighestScore;
		   }
		   
		   _view.updatehistoryscore();
	   }
	
	
	

}
