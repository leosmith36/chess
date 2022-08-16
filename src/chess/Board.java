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
	
	private Player turn = Player.WHITE;

	public Board() {
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		new Window(this);
		this.setLayout(null);

		for (int i = 0; i < SIZE; i++) {
			if (i == 0 || i == 7) {
				addPiece(new Rook(this, i, 0, Player.BLACK));
				addPiece(new Rook(this, i, 7, Player.WHITE));
			}else if (i == 1 || i == 6) {
				addPiece(new Knight(this, i, 0, Player.BLACK));
				addPiece(new Knight(this, i, 7, Player.WHITE));
			}else if (i == 2 || i == 5) {
				addPiece(new Bishop(this, i, 0, Player.BLACK));
				addPiece(new Bishop(this, i, 7, Player.WHITE));
			}else if (i == 3) {
				addPiece(new Queen(this, i, 0, Player.BLACK));
				addPiece(new Queen(this, i, 7, Player.WHITE));
			}else if (i == 4) {
				addPiece(new King(this, i, 0, Player.BLACK));
				addPiece(new King(this, i, 7, Player.WHITE));
			}
			addPiece(new Pawn(this, i, 1, Player.BLACK));
			addPiece(new Pawn(this, i, 6, Player.WHITE));
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
		case KING:
			showKingMoves(piece);
			break;
		case KNIGHT:
			showKnightMoves(piece);
			break;
		case PAWN:
			showPawnMoves(piece);
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
			Piece spotPiece = getPiece(i, y);
			if (spotPiece == null) {
				addMoveMarker(piece, i, y);
			}else {
				addMark(spotPiece);
				break;
			}
			i--;
		}
		
		i = x + 1;
		while (i < SIZE) {
			Piece spotPiece = getPiece(i, y);
			if (spotPiece == null) {
				addMoveMarker(piece, i, y);
			}else {
				addMark(spotPiece);
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
			Piece spotPiece = getPiece(x, i);
			if (spotPiece == null) {
				addMoveMarker(piece, x, i);
			}else {
				addMark(spotPiece);
				break;
			}
			i--;
		}
		
		i = y + 1;
		while (i < SIZE) {
			Piece spotPiece = getPiece(x, i);
			if (spotPiece == null) {
				addMoveMarker(piece, x, i);
			}else {
				addMark(spotPiece);
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
			Piece spotPiece = getPiece(newX,newY);
			if (spotPiece == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(spotPiece);
				break;
			}
			newX--;
			newY--;
		}
		
		newX = x - 1;
		newY = y + 1;
		while (newX >= 0 && newY < SIZE) {
			Piece spotPiece = getPiece(newX,newY);
			if (spotPiece == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(spotPiece);
				break;
			}
			newX--;
			newY++;
		}
		
		newX = x + 1;
		newY = y + 1;
		while (newX < SIZE && newY < SIZE) {
			Piece spotPiece = getPiece(newX,newY);
			if (spotPiece == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(spotPiece);
				break;
			}
			newX++;
			newY++;
		}
	
		newX = x + 1;
		newY = y - 1;
		while (newX < SIZE && newY >= 0) {
			Piece spotPiece = getPiece(newX,newY);
			if (spotPiece == null) {
				addMoveMarker(piece, newX, newY);
			}else {
				addMark(spotPiece);
				break;
			}
			newX++;
			newY--;
		}
	}
	
	public void showPawnMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		Piece spotPiece;
		int newY, newX;
		
		int dir = switch(piece.getPlayer()) {
		case WHITE -> -1;
		case BLACK -> 1;
		default -> 1;
		};
		
		newY = y + dir;
		if (newY < SIZE && newY >= 0) {
			spotPiece = getPiece(x, newY);
			if (spotPiece == null) {
				addMoveMarker(piece, x, newY);
			}
		}
		
		int newYStart = y + 2 * dir;
		if (newYStart < SIZE && newYStart >= 0 && !piece.getHasMoved()) {
			spotPiece = getPiece(x, newYStart);
			if (spotPiece == null) {
				addMoveMarker(piece, x, newYStart);
			}
		}
		
		newX = x - 1;
		if (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE) {
			spotPiece = getPiece(newX,newY);
			if (spotPiece != null) {
				addMark(spotPiece);
			}
		}
		
		newX = x + 1;
		if (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE) {
			spotPiece = getPiece(newX, newY);
			if (spotPiece != null) {
				addMark(spotPiece);
			}
		}
	}
	
	public void showKnightMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		
		int newXFirst, newYFirst, newXSecond, newYSecond;
		Piece spotPiece;
		
		for (int i = -2; i <= 2; i += 4) {
			newXFirst = x + i;
			newYFirst = y + i;
			
			if (newXFirst >= 0 && newXFirst < SIZE) {
				for (int j = -1; j <= 1; j += 2) {
					newYSecond = y + j;
					if (newYSecond >= 0 && newYSecond < SIZE) {
						spotPiece = getPiece(newXFirst, newYSecond);
						if (spotPiece == null) {
							addMoveMarker(piece, newXFirst, newYSecond);
						}else {
							addMark(spotPiece);
						}
					}
				}
			}
			
			if (newYFirst >= 0 && newYFirst < SIZE) {
				for (int j = -1; j <= 1; j += 2) {
					newXSecond = x + j;
					if (newXSecond >= 0 && newXSecond < SIZE) {
						spotPiece = getPiece(newXSecond, newYFirst);
						if (spotPiece == null) {
							addMoveMarker(piece, newXSecond, newYFirst);
						}else {
							addMark(spotPiece);
						}
					}
				}
			}

		}
	}
	
	public void showKingMoves(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != 0) {
					int newX = x + i, newY = y + j;
					if (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE) {
						Piece spotPiece = getPiece(newX, newY);
						if (spotPiece == null) {
							addMoveMarker(piece, newX, newY);
						}else {
							addMark(spotPiece);
						}
					}
				}
			}
		}
	}
	
	public void movePiece(Piece piece, int newXTile, int newYTile) {
		int xTile = piece.getXTile(), yTile = piece.getYTile();
		board[yTile][xTile] = null;
		board[newYTile][newXTile] = piece;
		piece.setTiles(newXTile, newYTile);
		if (!piece.getHasMoved()) {
			piece.setHasMoved(true);
		}
		nextTurn();
	}
	
	public void removePiece(Piece piece) {
		int x = piece.getXTile(), y = piece.getYTile();
		this.remove(board[y][x]);
		board[y][x] = null;
	}
	
	public void addPiece(Piece piece) {
		board[piece.getYTile()][piece.getXTile()] = piece;
	}
	
	public Piece getPiece(int xTile, int yTile) {
		return board[yTile][xTile];
	}
	
	public void addMoveMarker(Piece piece, int xTile, int yTile) {
		moves.add(new Move(this, piece, xTile, yTile));
	}
	
	public Piece getCurrentPiece() {
		return currentPiece;
	}
	
	public void addMark(Piece piece) {
		if (currentPiece.getPlayer() != piece.getPlayer()) {
			marks.add(piece);
		}
	}
	
	public void nextTurn() {
		if (turn == Player.WHITE) {
			turn = Player.BLACK;
		}else {
			turn = Player.WHITE;
		}
	}
	
	public Player getTurn() {
		return turn;
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