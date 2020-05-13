import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FantasyNameGenerator extends JFrame {
	// Button and Window parameters
	private final static int BUTTON_WIDTH = 50;
	private final static int BUTTON_HEIGHT = 30;
	private int x = 50;
	private int y = 75;
	private int spacer = 5;
	private int multiplier = 1;

	// Declare and Instantiate Button, Label, and Panel Objects
	private JPanel panel = new JPanel();
	private JLabel namePrint = new JLabel();
	private JButton generate = new JButton("Generate Names");
	private JButton generateOneHundred = new JButton("Generate 100 Names");
	private JButton generateOneThousand = new JButton("Generate 1000 Names");
	private JButton sortButton = new JButton("Sort File");
	private JButton quit = new JButton("Quit");
	private JButton clearFile = new JButton("Clear File");

	// Arrays containing prefixes and suffixes for First and Last Names
	private String[] prefixFirstName = { "Ag", "Ard", "Ab", "Anto", "Aq", "Amy", "Ar", "Bi'", "Bo", "Ba", "Bao", "By", "Bit", "Coo", "Cah", "Cok", "Cyt", "Coq", "Dro", "Dra", "Dre", "Doh", "Dob",
			"Dog", "Er", "Ea", "E'", "Euo", "En", "Eik", "Eir", "Fen", "For", "Fen", "Fib", "Frei", "Fal", "Ferg", "Gi", "Go", "Git", "Gin", "Gern", "Garm", "Gol", "Hit", "Hil", "Hur", "Hok", "Hong",
			"Io", "Ion", "Irn", "Ir", "Ing", "I'", "Joh", "J'", "Jun", "Jerr", "Jono", "K'", "Khi", "Kor", "Khan", "Khor", "Khar", "L'", "Lor", "Longu", "Longi", "Lori", "Li'", "Le'", "Law", "Mors",
			"Ma", "Min", "Mox", "M'", "Nu", "N'", "Nat", "Norno", "Neb", "Nut", "O'", "Ohm", "Oast", "Owe", "Ogh", "Og", "Po", "Put", "Pout", "Port", "Quo", "Qor", "Q'", "Qi", "Ro", "Rae", "Rog",
			"Ryne", "Rogra", "Shu", "Sho", "Shann", "Shur", "Smaq", "S'", "Toro", "Tiqa", "Togo", "Taki", "Taleo", "Tarkin", "U", "U'", "Ung", "Uro", "Unang", "Una", "Umi", "Voro", "Vin", "Vut",
			"Vita", "Vaci", "Worgo", "Wilm", "Warr", "Wyl", "Wyk", "X'", "Xi", "Xe", "Xeq", "Xiao", "Z'", "Zema", "Zuro", "Zaket", "Zoroq", "Zadi" };

	private String[] suffixFirstName = { "ma", "gly", "ti", "go", "mi", "oot", "on", "it", "ri", "ir", "we", "ar", "io", "po", "lo", "in", "u", "o", "jo", "min", "wyn", "won", "hit", "das", "hoss",
			"hing", "nang", "jowyn", "or", "yog", "wyk", "per", "whys", "org", "bert", "tog", "toc", "estoc", "martok", "mardu", "sultai", "oros", "gruul", "gril", "ill", "ik", "sik", "dut", "ot",
			"oa", "af", "" };

	private String[] prefixLastName = { "Silver", "Gold", "Hammer", "Forge", "Imp", "Shadow", "Spin", "Gear", "Bronze", "Copper", "Draco", "Dragon", "Drago", "Drake", "Castle", "Swamp", "Island",
			"Mountain", "Light", "Stone", "Under", "Sky", "Ice", "Brim", "Barrow", "Gill", "Ocean", "Sea", "Wind", "Tar", "Angel", "Nature", "Fire", "Earth", "Smith", "Brew", "Grove", "Grand", "Hill",
			"River", "Carcass", "Necro", "Thunder", "Lightning", "Metal", "Servant", "Grave", "Rot", "Flutter", "Quick", "Hard", "Heaven", "Hell", "Bush", "Leaf", "Coral", "Fish", "Silt", "Sand",
			"Magma", "Lava", "Dirt", "Gravel", "Anvil", "Heavy", "Kill", "Worn", "Guilt", "Law", "Capital", "Steppe", "Air", "Warp", "Aether", "Magic", "Arcane", "Spell", "Demon", "Fel", "Gutter",
			"Sword", "Battle", "Lance", "Dire", "Liar", "Mire" };

	private String[] suffixLastName = { "forged", "borne", "holm", "high", "lain", "casted", "hero", "stone", "shadow", "light", "bust", "breaker", "lit", "hill", "rite", "hammer", "arm", "sworn",
			"learned", "bred", "foot", "sky", "blasted", "slayer", "daughter", "son", "lance", "sword", "land", "branded", "brand", "claimed", "heir", "torn", "reign", "sovereign", "struck",
			"blessed", "river", "blood", "fire", "earth", "ice", "aether", "starved", "stave", "craver" };

	// Declare and Instantiate a file writer
	private FantasyWriteFile file = new FantasyWriteFile();

	public static void main(String[] args) {
		FantasyNameGenerator gen = new FantasyNameGenerator();

		// Initialize the window and its components
		gen.init();

		// Then add functionality to the buttons
		gen.functionality();
	}

	private void init() {
		// set the Layout to null so the Layout takes on the parameters we define
		panel.setLayout(null);

		// Set the bounds for each of the buttons
		generate.setBounds(x, y, BUTTON_WIDTH * 4, BUTTON_HEIGHT);
		generateOneHundred.setBounds(x, y + (BUTTON_HEIGHT + spacer) * multiplier, BUTTON_WIDTH * 4, BUTTON_HEIGHT);
		generateOneThousand.setBounds(x, y + (BUTTON_HEIGHT + spacer) * (multiplier + 1), BUTTON_WIDTH * 4, BUTTON_HEIGHT);
		sortButton.setBounds(x, y + (BUTTON_HEIGHT + spacer) * (multiplier + 2), BUTTON_WIDTH * 4, BUTTON_HEIGHT);
		clearFile.setBounds(x, y + (BUTTON_HEIGHT + spacer) * (multiplier + 3), BUTTON_WIDTH * 4, BUTTON_HEIGHT);
		quit.setBounds(x, y + (BUTTON_HEIGHT + spacer) * (multiplier + 4), BUTTON_WIDTH * 4, BUTTON_HEIGHT);

		// Set the bounds, font color, and font type of the JLabel object
		namePrint.setBounds(x * 6, y + (BUTTON_WIDTH) / 2, BUTTON_WIDTH * 10, BUTTON_HEIGHT * 5);
		namePrint.setForeground(Color.BLACK);
		namePrint.setFont(new Font("SansSerif", Font.BOLD, 36));

		// Add each component to the panel
		panel.add(generate);
		panel.add(generateOneHundred);
		panel.add(generateOneThousand);
		panel.add(sortButton);
		panel.add(quit);
		panel.add(clearFile);
		panel.add(namePrint);

		// Adds the content pane to the panel
		getContentPane().add(panel);

		// Set the size of the window
		setSize(800, 400);

		// Set the close operation
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Ensure all the components are set visible
		setVisible(true);
	}

	private void functionality() {

		// Add functionality to the generate button
		generate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Combines the names and sets the text on the panel
				drawName();
			}
		});

		// Add functionality to the generate 100 button
		generateOneHundred.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 99; i++) {
					// Combines the names and outputs them to a file
					toNameFile();

					// Notifies the user when the file is being compiled and completed via panel text
					if (i == 0) {
						namePrint.setText("Processing...");
					} else {
						namePrint.setText("File Write Complete");
					}
				}
			}
		});

		// Set functionality to the generate 1000 button
		generateOneThousand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 999; i++) {
					// Combines the names and writes them to a file
					toNameFile();

					// Notifies the user when the file is being compiled and completed via panel text
					if (i == 0) {
						namePrint.setText("Processing...");
					} else {
						namePrint.setText("File Write Complete");
					}
				}
			}
		});

		sortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					file.sortFile();
					namePrint.setText("File Sorted");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Add functionality to the quit button
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Add functionality to the clear file button
		clearFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					file.clearOldData();
					namePrint.setText("File Deleted");
				} catch (NoSuchFileException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private int randomize(int size) {
		Random rand = new Random();
		int randInt = rand.nextInt(size - 1);
		return randInt;
	}

	private String compileName() {
		String name;
		int rngFNP = randomize(prefixFirstName.length);
		int rngFNS = randomize(suffixFirstName.length);
		int rngLNP = randomize(prefixLastName.length);
		int rngLNS = randomize(suffixLastName.length);

		if (suffixLastName[rngLNS].equalsIgnoreCase(prefixLastName[rngLNP])) {
			System.out.println("We get here");
			name = prefixFirstName[rngFNP] + suffixFirstName[rngFNS] + " of the " + prefixLastName[rngLNP];
			if (prefixLastName[rngLNP].equals("Fire"))
				name = prefixFirstName[rngFNP] + suffixFirstName[rngFNS] + " of the Flame";

		} else {
			name = prefixFirstName[rngFNP] + suffixFirstName[rngFNS] + " " + prefixLastName[rngLNP] + suffixLastName[rngLNS];

		}

		return name;
	}

	private void drawName() {
		String name = compileName();

		namePrint.setText(name);
	}

	private void toNameFile() {
		String name = compileName();
		try {
			file.fileManip(name);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
