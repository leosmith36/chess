package chess;

import java.awt.Color;
import java.awt.Font;

public abstract class Piece extends BoardObject{

	private static final long serialVersionUID = 3578791061852413527L;

	protected Pieces pieceType;
	
	protected boolean showingMoves = false;

	public Piece(Board board, Pieces type, int xTile, int yTile) {
		super(board, xTile, yTile);
		pieceType = type;
		this.setFont(new Font("arial", Font.PLAIN, 20));
		this.setForeground(Color.RED);
	}
	
	@Override
	public void boardAction() {
		if (showingMoves) {
			board.hideMoves();
			showingMoves = false;
		}else {
			board.showMoves(this);
			showingMoves = true;
		}
		board.repaint();
	}

	public Pieces getType() {
		return pieceType;
	}
	
	
}
