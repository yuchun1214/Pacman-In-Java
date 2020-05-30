package pacMan;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

import org.json.JSONArray;


import javax.swing.JFrame;


public class Map extends JFrame{
	
	ArrayList<ArrayList<Dot> >Dots;
	
	public Map() throws IOException {
		// TODO Auto-generated constructor stub
		
		// Frame Initialize
		super();
		setSize(567, 585);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setContentPane(new MapBackground());
		// Dots initialize
		Dots = new ArrayList<ArrayList<Dot> >();
		ArrayList<Dot> row;
		Dot temp;
		for(int i = 0; i < 570; i += 22) {
			row = new ArrayList<Dot>();
			for(int j = 0; j < 570; j += 22) {
				temp = new Dot(i, j, 15);
				row.add(temp);
				add(temp);
			}
			Dots.add(row);
		}
		
		File file = new File("src/pacMan/mazeconfig.json");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();
		String config = new String(data, "UTF-8");
		JSONArray jar = new JSONArray(config);
		JSONArray temp1;
		JSONArray temp2;
		for(int i = 0; i < jar.length(); ++i) {
			// System.out.println(jar.get(i));
			temp1 = new JSONArray(jar.get(i).toString()); // Dots.get(jar.get(i))
			// System.out.println(temp1);
			Dots.get(temp1.getInt(0)).get(temp1.getInt(1)).setWall();
			Dots.get(25 - temp1.getInt(0)).get(temp1.getInt(1)).setWall();
		}
		
		setVisible(true);

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Map map = new Map();
	}

}
