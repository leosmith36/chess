package chess;

public class Bishop extends Piece{

	private static final long serialVersionUID = 5141876944752687720L;
	
	public Bishop(Board board, int xTile, int yTile) {
		super(board, Pieces.BISHOP, xTile, yTile);
		this.setText("B");
	}





}
