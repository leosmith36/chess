package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Rook extends Piece {

	private static final long serialVersionUID = 1355241352435787703L;

	public Rook(Board board, int xTile, int yTile, Player player) {
		super(board, Pieces.ROOK, xTile, yTile, player);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (player == Player.WHITE) {
			g.setColor(Color.WHITE);
		}else {
			g.setColor(Color.BLACK);
		}
		g.drawString("R", Board.TILE / 2, Board.TILE / 2);
	}


}
