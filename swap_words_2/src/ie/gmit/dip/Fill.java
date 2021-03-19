package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public abstract class Fill {
	
	private String input;
	private Map<String, String> map = new HashMap<>();
	
	public void fill(String input) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(input))));
		String word = new String();
		while ((word = br.readLine()) != null) {
			map.put(word, word); //for Google 1000 words to map to themselves
			//System.out.print("This is the map: " + map);
		}
		br.close();
		}
	}
