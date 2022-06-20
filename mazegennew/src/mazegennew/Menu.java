package mazegennew;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class Menu extends JFrame implements ActionListener{
	
	private JPanel panel = new JPanel();
	private SpinnerModel sm1 = new SpinnerNumberModel(10, 2, Integer.MAX_VALUE, 1);
	private SpinnerModel sm2 = new SpinnerNumberModel(50, 5, 400, 1);
	private JSpinner spinnerC = new JSpinner(sm1);
	private JSpinner spinnerS = new JSpinner(sm2);
	private JLabel labelC = new JLabel("Cell size: ");
	private JLabel labelS = new JLabel("Map size: ");
	private JButton def = new JButton("Growing Tree");
	private JButton genMaze = new JButton("Aldous-Broder");
	
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Maze");
		setSize(300,300);
		getContentPane().add(panel);
		panel.setLayout(new GridLayout(3,2));
		panel.add(labelC);
		panel.add(spinnerC);
		panel.add(labelS);
		panel.add(spinnerS);
		panel.add(def);
		panel.add(genMaze);
		def.addActionListener(this);
		genMaze.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == def) {
			new Window((int) spinnerS.getValue(),(int) spinnerC.getValue(),1);
		}
		if(e.getSource() == genMaze) {
			new Window((int) spinnerS.getValue(),(int) spinnerC.getValue(),0);
		}

	}

}
