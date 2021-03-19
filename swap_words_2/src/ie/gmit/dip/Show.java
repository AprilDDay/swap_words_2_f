package ie.gmit.dip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Show extends Fill implements Database{ 
	
	public Show() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static Scanner scanner;
	private static String userInput = new String();
	private boolean keepRunning = true;

	private static boolean fill=false;
	
	//private String input = new String();
	
	private String[] words;
	private String googleWord;
	
	private Map<String, String> map = new HashMap<>();
	private Set<String> set = new HashSet<>();
			
	public void start() {
		scanner = new Scanner(System.in);
		load();
		Show database = new Show();
		try {
			database.createSet();	
			database.initialise();
			super.fill(Files.googleWordFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while(true) {
			System.out.print("Enter Text>");	
		
			userInput = scanner.nextLine().toString();
			
			getGoogleEquivalent(userInput);
			
			System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);		
			System.out.println(ConsoleColour.RESET);
			
		}
	}

	
	public void createSet() throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Files.googleWordFile))));
		String line = new String();
		
		while ((line = br.readLine()) != null) {
			set.add(line);
			//System.out.println("This is the RUNNER set: "+set);
		}		
		br.close();
	}
	
	public void initialise() throws Exception {		
		String line = new String();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File(Files.mobyThesaurus2File))));
		String word = new String();
		googleWord = null;
		while ((line = br2.readLine()) !=null) {
			words = line.split(",");
			for (String w: words) {
				if(set.contains(w)){
					fill =  true;
					googleWord = w;
					//exec(fill);
					addAll(words, googleWord);
					 if(set.contains(w)) {
						 googleWord = w;
						 map.put(word, googleWord);
					 }
						break;
			}
			}
		}
	}		
	

	private void addAll(String[] words, String googleWord) {
		for(String word: words) {
			map.put(word,googleWord);	
			}
	}
		

		public String getGoogleEquivalent(String userInput) {

			String[] temps = userInput.split(" ");//this works
			String output = new String();
			for (int i=0; i<temps.length; i++) {
				if(map.containsKey(temps[i])) {	
					temps[i] = map.get(temps[i]);
					System.out.println("This is temp: " + temps[i]);//confirmed swap was done
				}else {
					System.out.println("This won't swap: " + temps[i]);
					temps[i] = temps[i];
				}		
			}
			
			StringBuffer outNow = new StringBuffer();
			
			for (int i=0; i<temps.length; i++) {
				outNow.append(temps[i] + " ");			
			}

			System.out.print(outNow);
			return output;
		}

		
		public void load() {
		
		System.out.println(ConsoleColour.BLUE_BRIGHT);
		System.out.println("***************************************************");
		System.out.println("* GMIT - Dept. Computer Science & Applied Physics *");
		System.out.println("*                                                 *");
		System.out.println("*             Text Simplifier V0.1                *");
		System.out.println("*       (AKA Orwellian Language Compliance)       *");		
		System.out.println("*                                                 *");
		System.out.println("***************************************************");
	
		}
		
}
