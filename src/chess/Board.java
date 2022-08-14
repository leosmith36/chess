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
	private LinkedList<Piece> marks = new LinkedList<Piece>(); 
	
	private Piece currentPiece = null;

	public Board() {
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		new Window(this);
		this.setLayout(null);

		for (int i = 0; i < SIZE; i++) {
			if (i == 0) {
				board[0][0] = new Rook(this, 0, 0);
			}else if (i == 2) {
				board[0][2] = new Bishop(this, 2, 0);
			}else if (i == 3) {
				board[0][3] = new Queen(this, 3, 0);
			}
		}
		
		this.repaint();
	}
	
	public void showMoves(Piece piece) {
		currentPiece = piece;
		Pieces type = piece.getType();
		switch (type) {
		case BISHOP:
			showDiagonalMoves(piece);
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
			showDiagonalMoves(piece);
			showHorizontalMoves(piece);
			showVerticalMoves(piece);
			break;
		case ROOK:
			showVerticalMoves(piece);
			showHorizontalMoves(piece);
			break;
		default:
			break;
		
		}
	}
	
	public void hideMoves() {
		currentPiece = null;
		moves.forEach(item -> this.remove(item));
		moves.clear();
		marks.clear();
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
	
	public void showHorizontalMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		int i;
		i = x - 1;
		while (i >= 0) {
			if (board[y][i] == null) {
				addMoveMarker(piece, i, y);
			}else {
				addMark(board[y][i]);
				break;
			}
			i--;
		}
		
		i = x + 1;
		while (i < SIZE) {
			if (board[y][i] == null) {
				addMoveMarker(piece, i, y);
			}else {
				addMark(board[y][i]);
				break;
			}
			i++;
		}
	}
	
	public void showVerticalMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		int i;
		i = y - 1;
		while (i >= 0) {
			if (board[i][x] == null) {
				addMoveMarker(piece, x, i);
			}else {
				addMark(board[i][x]);
				break;
			}
			i--;
		}
		
		i = y + 1;
		while (i < SIZE) {
			if (board[i][x] == null) {
				addMoveMarker(piece, x, i);
			}else {
				addMark(board[i][x]);
				break;
			}
			i++;
		}
	}
	
	public void showDiagonalMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		
		int newX, newY;
		
		newX = x - 1;
		newY = y - 1;
		while (newX >= 0 && newY >= 0) {
			if (board[newY][newX] == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(board[newY][newX]);
				break;
			}
			newX--;
			newY--;
		}
		
		newX = x - 1;
		newY = y + 1;
		while (newX >= 0 && newY < SIZE) {
			if (board[newY][newX] == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(board[newY][newX]);
				break;
			}
			newX--;
			newY++;
		}
		
		newX = x + 1;
		newY = y + 1;
		while (newX < SIZE && newY < SIZE) {
			if (board[newY][newX] == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(board[newY][newX]);
				break;
			}
			newX++;
			newY++;
		}
		
		newX = x + 1;
		newY = y - 1;
		while (newX < SIZE && newY >= 0) {
			if (board[newY][newX] == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(board[newY][newX]);
				break;
			}
			newX++;
			newY--;
		}
	}
	
	public void movePiece(Piece piece, int newXTile, int newYTile) {
		int xTile = piece.getXTile(), yTile = piece.getYTile();
		board[yTile][xTile] = null;
		board[newYTile][newXTile] = piece;
		piece.setTiles(newXTile, newYTile);
	}
	
	public void addMoveMarker(Piece piece, int xTile, int yTile) {
		moves.add(new Move(this, piece, xTile, yTile));
	}
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	
	public void addMark(Piece piece) {
		marks.add(piece);
	}
	
	public LinkedList<Piece> getMarks(){
		return marks;
	}
	
	public static int getPositionFromTile(int n) {
		return n * TILE + MARGIN;
	}
	
	public static void main(String[] args) {
		new Board();
	}

}