/**
 * Simple text editor designed to look and operate in an identical way 
 * as the windows application NotePad
 * 
 * @author Michael Tallie
 */

import java.awt.Desktop.Action;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class MyNotePad extends JFrame {
	
	private JTextArea textArea = new JTextArea(20, 60);
	private JFileChooser fc = new JFileChooser();
	
	public MyNotePad() {
		textArea.setTabSize(5);
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);
		
		FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Plain text", "txt");
		fc.setFileFilter(textFilter);
		
		// Create Menu Bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		// Add File menu item and tree
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem menuItem = file.add(New);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		JMenuItem menuItem_1 = file.add(Open);
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		JMenuItem menuItem_2 = file.add(Save);
		menuItem_2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		file.add(SaveAs);
		file.addSeparator();
		file.add(PageSetup);
		JMenuItem menuItem_3 = file.add(Print);
		menuItem_3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		file.addSeparator();
		file.add(Exit);
		
		// Add Edit menu item and tree
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		
		JMenuItem menuItem_4 = edit.add(Undo);
		menuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		edit.addSeparator();
		JMenuItem menuItem_5 = edit.add(Cut);
		menuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		JMenuItem menuItem_6 = edit.add(Copy);
		menuItem_6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		JMenuItem menuItem_7 = edit.add(Paste);
		menuItem_7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		JMenuItem menuItem_8 = edit.add(Delete);
		menuItem_8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		edit.addSeparator();
		JMenuItem menuItem_9 = edit.add(Find);
		menuItem_9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		JMenuItem menuItem_10 = edit.add(FindNext);
		menuItem_10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		JMenuItem menuItem_11 = edit.add(Replace);
		menuItem_11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		JMenuItem menuItem_12 = edit.add(GoTo);
		menuItem_12.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		edit.addSeparator();
		JMenuItem menuItem_13 = edit.add(SelectAll);
		menuItem_13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		JMenuItem menuItem_14 = edit.add(TimeDate);
		menuItem_14.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		
		// Add Format menu item and tree
		JMenu format = new JMenu("Format");
		menuBar.add(format);
		
		format.add(WordWrap);
		format.add(Font);
		
		// Add View menu item and tree
		JMenu view = new JMenu("View");
		menuBar.add(view);
		
		view.add(StatusBar);
		
		// Add Help menu item and tree
		JMenu help = new JMenu("Help");
		menuBar.add(help);
		
		help.add(ViewHelp);
		help.addSeparator();
		help.add(AboutMyNotePad);
		
		// JFrame options
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	// Actions below here
	/**
	 * New Abstraction
	 * Performs action on click
	 * 
	 * @uses newFile() method
	 */
	AbstractAction New = new AbstractAction("New") {
		@Override
		public void actionPerformed(ActionEvent e) {
			newFile();
		}
	};
	
	/**
	 * Open Abstraction
	 * Performs action on click
	 * 
	 * @uses openFile(String) method
	 */
	AbstractAction Open = new AbstractAction("Open...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				openFile(fc.getSelectedFile().getAbsolutePath());
			}
		}
	};
	
	/**
	 * Save Abstraction
	 * Performs action on click
	 * 
	 * @uses saveFile() method
	 */
	AbstractAction Save = new AbstractAction("Save") {
		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile();
		}
	};
	
	/**
	 * Save As Abstraction
	 * Performs action on click
	 * 
	 * @uses saveAs() method
	 */
	AbstractAction SaveAs = new AbstractAction("Save As...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Page Setup Abstraction
	 * Performs action on click
	 */
	AbstractAction PageSetup = new AbstractAction("Page Setup...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Print Abstraction
	 * Performs action on click
	 */
	AbstractAction Print = new AbstractAction("Print...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Exit Abstraction
	 * Performs action on click
	 */
	AbstractAction Exit = new AbstractAction("Exit") {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	/**
	 * Undo Abstraction
	 * Performs action on click
	 */
	AbstractAction Undo = new AbstractAction("Undo") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Cut Abstraction
	 * Performs action on click
	 */
	AbstractAction Cut = new AbstractAction("Cut") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Copy Abstraction
	 * Performs action on click
	 */
	AbstractAction Copy = new AbstractAction("Copy") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Paste Abstraction
	 * Performs action on click
	 */
	AbstractAction Paste = new AbstractAction("Paste") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Delete Abstraction
	 * Performs action on click
	 */
	AbstractAction Delete = new AbstractAction("Delete") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Find Abstraction
	 * Performs action on click
	 */
	AbstractAction Find = new AbstractAction("Find") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Find Next Abstraction
	 * Performs action on click
	 */
	AbstractAction FindNext = new AbstractAction("Find Next") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Replace Abstraction
	 * Performs action on click
	 */
	AbstractAction Replace = new AbstractAction("Replace...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Go To Abstraction
	 * Performs action on click
	 */
	AbstractAction GoTo = new AbstractAction("Go To...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Select All Abstraction
	 * Performs action on click
	 */
	AbstractAction SelectAll = new AbstractAction("Select All") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Time/Date Abstraction
	 * Performs action on click
	 */
	AbstractAction TimeDate = new AbstractAction("Time/Date") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Word wrap Abstraction
	 * Performs action on click
	 */
	AbstractAction WordWrap = new AbstractAction("Word wrap") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Font Abstraction
	 * Performs action on click
	 */
	AbstractAction Font = new AbstractAction("Font...") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * Status Bar Abstraction
	 * Performs action on click
	 */
	AbstractAction StatusBar = new AbstractAction("Status Bar") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * View Help Abstraction
	 * Performs action on click
	 */
	AbstractAction ViewHelp = new AbstractAction("View Help") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	/**
	 * About MyNotePad Abstraction
	 * Performs action on click
	 */
	AbstractAction AboutMyNotePad = new AbstractAction("About MyNotePad") {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	
	// Methods below this point
	/**
	 * Method clears the text area - textArea
	 */
	public void newFile() {
		textArea.setText("");
	}
	
	/**
	 * Takes a user defined file name and opens the file in the text area
	 * Checks to make sure the file exists
	 * 
	 * @param String fileName
	 */
	public void openFile(String fileName) {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
			textArea.read(fr, null);
			fr.close();
			setTitle(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens the save dialog box and allows user to choose a name to 
	 * save file as a text document
	 */
	public void saveFile() {
		if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			FileWriter fw = null;
			try {
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
				textArea.write(fw);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void main(String[] args) {
		new MyNotePad();
	}
}