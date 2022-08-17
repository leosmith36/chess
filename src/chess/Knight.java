package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Knight extends Piece {

	private static final long serialVersionUID = -6207909670070414840L;

	public Knight(Board board, int xTile, int yTile, Player player) {
		super(board, Pieces.KNIGHT, xTile, yTile, player, "N");
		// TODO Auto-generated constructor stub
	}
//
//	@Override
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		if (player == Player.WHITE) {
//			g.setColor(Color.WHITE);
//		}else {
//			g.setColor(Color.BLACK);
//		}
//		g.drawString("N", Board.TILE / 2, Board.TILE / 2);
//	}

}
