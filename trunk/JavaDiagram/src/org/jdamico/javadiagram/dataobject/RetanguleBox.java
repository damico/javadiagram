package org.jdamico.javadiagram.dataobject;

public class RetanguleBox {

	private int height = 0;
	private int lenght = 0;
	private String caption;
	private int id = 0;
	private int source = 0;
	private int x = 0;
	private int y = 0;
	private int row = 0;
	private int col = 0;
	private int centerX = 0;
	
	
	
	public RetanguleBox(int height, int lenght, String caption, int id,
			int source, int x, int y, int row, int col, int centerX) {
		super();
		this.height = height;
		this.lenght = lenght;
		this.caption = caption;
		this.id = id;
		this.source = source;
		this.x = x;
		this.y = y;
		this.row = row;
		this.col = col;
		this.centerX = centerX;
	}
	
	
	public RetanguleBox(int height, int lenght, String caption, int id, int source,  int row, int col) {
		super();
		this.height = height;
		this.lenght = lenght;
		this.caption = caption;
		this.id = id;
		this.source = source;
		this.row = row;
		this.col = col;
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getCenter() {
		return centerX;
	}
	public void setCenter(int centerX) {
		this.centerX = centerX;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	
	
}
