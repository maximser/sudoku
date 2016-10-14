package sudoku;
//
//
//public class APP{
//
//	public static void main(String[] args) throws Exception {
//		int [][] table = {
//				{2,0,0,0,7,0,0,3,8},
//				{0,0,0,0,0,6,0,7,0},
//				{3,0,0,0,4,0,6,0,0},
//				{0,0,8,0,2,0,7,0,0},
//				{1,0,0,0,0,0,0,0,6},
//				{0,0,7,0,3,0,4,0,0},
//				{0,0,4,0,8,0,0,0,9},
//				{0,6,0,4,0,0,0,0,0},
//				{9,1,0,0,6,0,0,0,2}
//							};
//		int [][] table3 = {
//				{0,0,6,8,7,1,2,0,0},
//				{0,0,0,0,0,0,0,0,0},
//				{5,0,1,3,0,9,7,0,8},
//				{1,0,7,0,0,0,6,0,9},
//				{2,0,0,0,0,0,0,0,7},
//				{9,0,3,0,0,0,8,0,1},
//				{3,0,5,9,0,7,4,0,2},
//				{0,0,0,0,0,0,0,0,0},
//				{0,0,2,4,3,5,1,0,0}
//							};
//		int [][] table4 = {
//				{0,0,0,0,0,0,0,7,9},
//				{8,2,0,0,4,0,0,0,5},
//				{0,1,6,0,7,0,0,0,2},
//				{0,0,0,0,1,9,6,0,0},
//				{0,0,1,0,0,2,0,5,0},
//				{3,0,0,5,0,0,0,0,0},
//				{0,9,0,0,0,8,0,0,0},
//				{5,0,0,3,0,0,4,0,0},
//				{0,0,0,0,0,1,0,0,0},
//							};
//		Sudoku q = new Sudoku(table4);
//		System.out.println(q.toString()+"\n");
//
//		long start = System.nanoTime();
//		Solver s = new Solver(q);
//		Sudoku res = s.solve();
//		long end = System.nanoTime();
//		System.out.println(res.toString());
//		System.out.println("Решено за "+(end-start)/1000000000.+"с");
//		}
//}
//
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.text.MaskFormatter;


public class APP implements PropertyChangeListener, ActionListener{
	private int [][] model = new int[9][9];
	private HashMap<JFormattedTextField, String> map = new HashMap<JFormattedTextField, String>();
	private JFrame aboutF = null;
	public APP() throws ParseException {
		JFrame jfr = new JFrame("Sudoku solver");
		jfr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfr.setSize(240, 240);
		jfr.setMinimumSize(new Dimension(240, 240));
		jfr.setLayout(new GridLayout(3, 3));

		MaskFormatter mf = new MaskFormatter("#");
		for (int k = 0; k < 9; k++) {
			JPanel p = new JPanel(new GridLayout(3, 3));
			p.setPreferredSize(new Dimension(75, 75));
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					JFormattedTextField t = new JFormattedTextField(mf);
					map.put(t, (i+3*(k/3))+""+(j+3*(k%3)));
					t.addPropertyChangeListener("value", this);

					p.add(t);
				}
			}
			p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			p.setOpaque(true);
			jfr.getContentPane().add(p);
		}

		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem neww = new JMenuItem("New");
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		JMenuItem solve = new JMenuItem("Solve");
		open.setEnabled(false);
		save.setEnabled(false);
		neww.addActionListener(this);
		neww.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.META_MASK));
		open.addActionListener(this);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_MASK));
		save.addActionListener(this);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_MASK));
		solve.addActionListener(this);
		solve.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.META_MASK));
		file.add(neww);
		file.add(open);
		file.add(save);
		file.addSeparator();
		file.add(solve);

		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(aboutF==null){

				aboutF = new JFrame("About");
				aboutF.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent windowEvent){
						aboutF = null;
			         }
				});

				aboutF.setSize(440, 240);
				aboutF.setMinimumSize(new Dimension(440, 240));
				aboutF.setMaximumSize(new Dimension(440, 240));

				JLabel l = new JLabel("<html>'sudoku solver' - solve basic 9x9 <br>Version 0.9<br> https://github.com/maximser/sudoku",JLabel.CENTER);
				l.setSize(200,100);
				aboutF.getContentPane().add(l);
				aboutF.setVisible(true);
				}
				System.out.println(about.toString());
			}
		});
		help.add(about);
		bar.add(file);
		bar.add(help);
		jfr.setJMenuBar(bar);
		jfr.setVisible(true);
	}


	public static void main(String[] args) {
		try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
    }
    catch(InstantiationException e) {
            System.out.println("InstantiationException: " + e.getMessage());
    }
    catch(IllegalAccessException e) {
            System.out.println("IllegalAccessException: " + e.getMessage());
    }
    catch(UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException: " + e.getMessage());
    }


		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					new APP();
				} catch (ParseException e) {
					e.printStackTrace();
				}

			}
		});

	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		String t = (String) e.getNewValue();
		if(t!=null && !t.isEmpty()){
			String pos = map.get(((JFormattedTextField)e.getSource()));

			int i = Integer.parseInt(pos.charAt(0)+"");
			int j = Integer.parseInt(pos.charAt(1)+"");
			model[i][j] = Integer.parseInt(t);
		}

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "New":
			for (int i = 0; i < model.length; i++) {
				Arrays.fill(model[i],0);
			}
			for (JFormattedTextField t : map.keySet()) {
				t.setValue("");
			}

			break;
		case "Save":

			break;
		case "Open":
			updateTextFromModel();
			break;
		case "Solve":
			try {
				model = (new Solver(new Sudoku(model))).solve().getAll();
				updateTextFromModel();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

			break;
		}

	}
	void updateTextFromModel(){
		for (JFormattedTextField t : map.keySet()) {
			String pos = map.get(t);
			int i = Integer.parseInt(pos.charAt(0)+"");
			int j = Integer.parseInt(pos.charAt(1)+"");
			t.setText(model[i][j]+"");
		}
	}
}

