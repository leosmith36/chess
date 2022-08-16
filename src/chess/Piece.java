package chess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.border.Border;

public abstract class Piece extends BoardObject{

	private static final long serialVersionUID = 3578791061852413527L;

	protected Pieces pieceType;

	protected Player player;
	
	public Piece(Board board, Pieces type, int xTile, int yTile, Player player) {
		super(board, xTile, yTile, JLayeredPane.DRAG_LAYER);
		pieceType = type;
		this.player = player;
		this.setFont(new Font("arial", Font.PLAIN, 20));
		switch (player) {
		case BLACK:
			this.setForeground(Color.WHITE);
			break;
		case WHITE:
			this.setForeground(Color.BLACK);
			break;
		default:
			break;
		
		}
	}
	
	@Override
	public void boardAction() {
		if (board.getTurn() != player) {
			return;
		}
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
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (board != null && board.getMarks().contains(this)){
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, Board.TILE, Board.TILE);
		}
		g.setColor(Color.GRAY);
		g.fillRect(10, 10, Board.TILE - 20, Board.TILE - 20);
		
	}
}
