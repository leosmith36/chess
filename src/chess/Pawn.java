package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Pawn extends Piece {
	
	private static final long serialVersionUID = -7013175307196640189L;

	public Pawn(Board board, int xTile, int yTile, Player player) {
		super(board, Pieces.PAWN, xTile, yTile, player);
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
		g.drawString("P", Board.TILE / 2, Board.TILE / 2);
	}


}
