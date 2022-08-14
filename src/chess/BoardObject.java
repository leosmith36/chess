package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public abstract class BoardObject extends JButton{

	private static final long serialVersionUID = 4356971725631116472L;
	
	protected Point tileLocation;
	
	protected Board board;

	public BoardObject(Board board, int xTile, int yTile, int layer) {
		super();
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		setTiles(xTile, yTile);
		setSize(new Dimension(Board.TILE, Board.TILE));
		board.add(this, layer);
		this.board = board;
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boardAction();
				board.repaint();
			}
			
		});
	}
	
	public abstract void boardAction();
	
	public int getXTile() {
		return tileLocation.x;
	}
	
	public int getYTile() {
		return tileLocation.y;
	}
	
	public Point getTiles() {
		return tileLocation;
	}
	
	public void setTiles(Point point) {
		tileLocation = point;
		setLocation(Board.getPositionFromTile(point.x), Board.getPositionFromTile(point.y));
	}
	
	public void setTiles(int xTile, int yTile) {
		setTiles(new Point (xTile, yTile));
	}
}
