package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Move extends BoardObject{

	private static final long serialVersionUID = 2940948011444781220L;
	
	private Piece piece;

	public Move(Board board, Piece piece, int xTile, int yTile) {
		super(board, xTile, yTile);
		this.piece = piece;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillOval(0, 0, Board.TILE, Board.TILE);
		
	}

	@Override
	public void boardAction() {
		board.movePiece(piece, tileLocation.x, tileLocation.y);
		board.hideMoves();
		
	}

}
