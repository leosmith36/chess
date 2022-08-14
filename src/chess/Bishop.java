package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Bishop extends Piece{

	private static final long serialVersionUID = 5141876944752687720L;
	
	public Bishop(Board board, int xTile, int yTile, Player player) {
		super(board, Pieces.BISHOP, xTile, yTile, player);
		this.setText("B");
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (player == Player.WHITE) {
			g.setColor(Color.WHITE);
		}else {
			g.setColor(Color.BLACK);
		}
		g.drawString("B", Board.TILE / 2, Board.TILE / 2);
	}





}
