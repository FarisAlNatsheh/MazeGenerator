package mazegennew;

import java.awt.Graphics;

public class Cell {
	private boolean top, bottom, left, right, visited;
	private int x, y;
	public void drawCell(Graphics g, int x, int y, int width, int height) {
		if(top)
			g.drawLine(x,y, x+width,y);
		if(left)
			g.drawLine(x,y, x,y+height);
		if(right)
			g.drawLine(x+width,y, x+width,y+height);
		if(bottom)
			g.drawLine(x,y+height, x+width,y+height);
	}
	public Cell(int x, int y) {
		this.setX(x);
		this.setY(y);
		top = true;
		bottom = true;
		left = true;
		right = true;
	}
	public boolean isTop() {
		return top;
	}

	public void setTop(boolean up) {
		this.top = up;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean down) {
		this.bottom = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}


}
