package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JLayeredPane;

public class Board extends JLayeredPane {

	private static final long serialVersionUID = 1L;
	
	public static final int SIZE = 8;
	public static final int MARGIN = 100;
	public static final int TILE = 50;
	
	private Piece[][] board = new Piece[8][8];
	
	private LinkedList<Move> moves = new LinkedList<Move>();

	public Board() {
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		new Window(this);
		this.setLayout(null);

		for (int i = 0; i < SIZE; i++) {
			if (i == 0) {
				board[0][0] = new Rook(this, 0, 0);
			}else if (i == 2) {
				board[2][4] = new Bishop(this, 2, 4);
			}
		}
		
		this.repaint();
	}
	
	public void showMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		Pieces type = piece.getType();
		switch (type) {
		case BISHOP:
			showDiagonalMoves(x, y, piece);
			break;
		case EMPTY:
			break;
		case KING:
			break;
		case KNIGHT:
			break;
		case PAWN:
			break;
		case QUEEN:
			break;
		case ROOK:
			showVerticalMoves(x, piece);
			showHorizontalMoves(y, piece);
			break;
		default:
			break;
		
		}
	}
	
	public void hideMoves() {
		moves.forEach(item -> this.remove(item));
		moves.clear();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		
		for (int i = 0; i < SIZE; i++) {
			int y = i * TILE + MARGIN;
			for (int j = 0; j < SIZE; j++) {
				int x = j * TILE + MARGIN;
				if (j % 2 == 0) {
					if (i % 2 == 0) {
						g.setColor(Color.BLACK);
					}else {
						g.setColor(Color.WHITE);
					}
				}else {
					if (i % 2 == 0) {
						g.setColor(Color.WHITE);
					}else {
						g.setColor(Color.BLACK);
					}
				}
				g.fillRect(x, y, 50, 50);
			}
		}
	}
	
	public void showHorizontalMoves(int y, Piece piece) {
		for (int i = 0; i < SIZE; i++) {
			if (board[i][y] == null) {
				moves.add(new Move(this, piece, i, y));
			}
		}
	}
	
	public void showVerticalMoves(int x, Piece piece) {
		for (int i = 0; i < SIZE; i++) {
			if (board[x][i] == null) {
				moves.add(new Move(this, piece, x, i));
			}
		}
	}
	
	public void showDiagonalMoves(int x, int y, Piece piece) {
		int diff = Math.abs(x - y);
		for (int i = 0; i < SIZE - diff; i++) {
			int newX, newY;
			if (x < y) {
				newX = i;
				newY = i + diff;
			}else if (y < x) {
				newX = i + diff;
				newY = i;
			}else {
				newX = newY = 0;
			}
			if (board[newX][newY] == null) {
				moves.add(new Move(this, piece, newX, newY));
			}
		}
	}
	
	public void movePiece(Piece piece, int newXTile, int newYTile) {
		int xTile = piece.getXTile(), yTile = piece.getYTile();
		board[xTile][yTile] = null;
		board[newXTile][newYTile] = piece;
		piece.setTiles(newXTile, newYTile);
	}
	
	public static int getPositionFromTile(int n) {
		return n * TILE + MARGIN;
	}
	
	public static void main(String[] args) {
		new Board();
	}

}