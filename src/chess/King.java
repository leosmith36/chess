package chess;

import java.awt.Color;
import java.awt.Graphics;

public class King extends Piece{

	private static final long serialVersionUID = -6077537406003447990L;

	public King(Board board, int xTile, int yTile, Player player) {
		super(board, Pieces.KING, xTile, yTile, player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (player == Player.WHITE) {
			g.setColor(Color.WHITE);
		}else {
			g.setColor(Color.BLACK);
		}
		g.drawString("Ki", Board.TILE / 2, Board.TILE / 2);
	}

}
