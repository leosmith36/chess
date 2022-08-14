package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLayeredPane;

public abstract class Piece extends BoardObject{

	private static final long serialVersionUID = 3578791061852413527L;

	protected Pieces pieceType;

	public Piece(Board board, Pieces type, int xTile, int yTile) {
		super(board, xTile, yTile, JLayeredPane.DRAG_LAYER);
		pieceType = type;
		this.setFont(new Font("arial", Font.PLAIN, 20));
		this.setForeground(Color.RED);
	}
	
	@Override
	public void boardAction() {
		if (board.getCurrentPiece() == this) {
			board.hideMoves();
		}else if (board.getCurrentPiece() != null){
			if (board.getMarks().contains(this)) {
				board.removePiece(this);
				board.movePiece(board.getCurrentPiece(), getXTile(), getYTile());
				board.hideMoves();
			}else {
				board.hideMoves();
				board.showMoves(this);
			}
		}else {
			board.showMoves(this);
		}
		board.repaint();
	}

	public Pieces getType() {
		return pieceType;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (board != null && board.getMarks().contains(this)){
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Board.TILE, Board.TILE);
		}
	}
}
