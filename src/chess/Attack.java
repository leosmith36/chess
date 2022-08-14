package chess;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLayeredPane;

public class Attack extends BoardObject {

	private static final long serialVersionUID = 7931594147830530804L;
	
	public Attack(Board board, int xTile, int yTile) {
		super(board, xTile, yTile, JLayeredPane.DEFAULT_LAYER);
	}

	@Override
	public void boardAction() {
		System.out.println("KILL");
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, Board.TILE, Board.TILE);
	}
}
