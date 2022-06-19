package mazegennew;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements KeyListener{
	private int SIZE = 10;
	private Cell cells[][] = new Cell[SIZE][SIZE];
	private int CELL_SIZE = 30;
	private int visited=0;
	private JPanel panel, drawPanel;
	private int iterations = 0;
	public int rand(int min, int max) {
		return (int)(Math.random()*(max-min+1)+min);
	}

	@SuppressWarnings("serial")
	public Window(int size, int cellsize) {
		SIZE = size;
		CELL_SIZE = cellsize;
		cells = new Cell[SIZE][SIZE];
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Maze");
		//setSize(500,500);
		addKeyListener(this);

		for(int i= 0;i < cells.length; i++) {
			for(int j= 0;j < cells[0].length; j++) {
				cells[i][j] = new Cell(i,j);
			}
		}
		maze(cells[rand(0,SIZE-1)][rand(0,SIZE-1)]);
		panel = new JPanel();
		drawPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				for(int i= 0;i < cells.length; i++) {
					for(int j= 0;j < cells[0].length; j++) {
						cells[i][j].drawCell(g, i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
					}
				}

				panel.repaint();
			}
		};
		panel.setPreferredSize(new Dimension(SIZE*CELL_SIZE+1,SIZE*CELL_SIZE+1));
		getContentPane().add(panel);
		pack();
		drawPanel.setBackground(Color.white);
		
		panel.setLayout(new GridLayout(1,1));
		panel.add(drawPanel);
		setVisible(true);
	}
	public void maze(Cell c) {
		iterations++;
		if(visited >= SIZE*SIZE) {
			System.out.println("Iterations: " + iterations);
			iterations = 0;
			return;
		}
		int side = rand(1,4);
		switch(side) {

		case 1: //left
			if(inBounds(c.getX()-1)) {
				if(!cells[c.getX()-1][c.getY()].isVisited()) {
					cells[c.getX()-1][c.getY()].setRight(false);
					cells[c.getX()-1][c.getY()].setVisited(true);
					cells[c.getX()][c.getY()].setLeft(false);
					visited++;
				}
				maze(cells[c.getX()-1][c.getY()]);
			}
			else {
				maze(c);
			}

			break;
		case 2: //right
			if(inBounds(c.getX()+1)) {
				if(!cells[c.getX()+1][c.getY()].isVisited()) {
					cells[c.getX()+1][c.getY()].setLeft(false);
					cells[c.getX()+1][c.getY()].setVisited(true);
					cells[c.getX()][c.getY()].setRight(false);
					visited++;
				}
				maze(cells[c.getX()+1][c.getY()]);
			}
			else {
				maze(c);
			}
			break;
		case 3: //top
			if(inBounds(c.getY()-1)) {
				if(!cells[c.getX()][c.getY()-1].isVisited()) {
					cells[c.getX()][c.getY()-1].setBottom(false);
					cells[c.getX()][c.getY()-1].setVisited(true);
					cells[c.getX()][c.getY()].setTop(false);
					visited++;
				}
				maze(cells[c.getX()][c.getY()-1]);
			}
			else {
				maze(c);
			}
			break;
		case 4: //bottom
			if(inBounds(c.getY()+1)) {
				if(!cells[c.getX()][c.getY()+1].isVisited()) {
					cells[c.getX()][c.getY()+1].setTop(false);
					cells[c.getX()][c.getY()+1].setVisited(true);
					cells[c.getX()][c.getY()].setBottom(false);
					visited++;
				}
				maze(cells[c.getX()][c.getY()+1]);
			}
			else {
				maze(c);
			}
			break;

		}

	}


	public boolean inBounds(int x) {
		return x>-1 && x < SIZE;

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			for(int i= 0;i < cells.length; i++) {
				for(int j= 0;j < cells[0].length; j++) {
					cells[i][j] = new Cell(i,j);
				}
			}
			visited = 0;
			maze(cells[rand(0,SIZE-1)][rand(0,SIZE-1)]);
			panel.repaint();

		}
		else if(e.getKeyCode() == KeyEvent.VK_S) {
			screenshot();
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void screenshot() {
		BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
		panel.paint(img.getGraphics());
		File outputfile = new File(SIZE + "X"+ SIZE +"CELL SIZE " + CELL_SIZE+ " MAZE.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
