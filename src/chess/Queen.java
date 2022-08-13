package chess;

public class Queen extends Piece {

	private static final long serialVersionUID = -7988684781218720667L;

	public Queen(Board board, int xTile, int yTile) {
		super(board, Pieces.QUEEN, xTile, yTile);
		setText("Q");
	}



}
