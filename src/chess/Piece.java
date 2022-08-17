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
	
	protected String letter;
	
	protected int w, h;
	
	public Piece(Board board, Pieces type, int xTile, int yTile, Player player, String letter) {
		super(board, xTile, yTile, JLayeredPane.DRAG_LAYER);
		pieceType = type;
		this.letter = letter;
		this.player = player;
		this.setFont(new Font("arial", Font.PLAIN, Board.TILE / 2));
		w = this.getFontMetrics(this.getFont()).stringWidth(letter);
		h = this.getFont().getSize();
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
			if (board.getMarks().contains(this) && board.getCurrentPiece() != null) {
				board.removePiece(this);
				board.movePiece(board.getCurrentPiece(), getXTile(), getYTile(), true);
				board.hideMoves();
				board.clearCurrentPiece();
			}
			return;
		}
		
		if (board.getCurrentPiece() == this) {
			board.hideMoves();
			board.clearCurrentPiece();
		}else if (board.getCurrentPiece() != null){
				board.hideMoves();
				board.clearCurrentPiece();
				board.showMoves(this, true);
		}else {
			board.showMoves(this, true);
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
		
		
		if (player == Player.WHITE) {
			g.setColor(Color.WHITE);
		}else {
			g.setColor(Color.BLACK);
		}

		g.drawString(letter, Board.TILE / 2 - w / 2, Board.TILE / 2 + h / 3);
	}
}
