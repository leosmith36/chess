package chess;

import java.awt.Color;
import java.awt.Graphics;

public class Queen extends Piece {

	private static final long serialVersionUID = -7988684781218720667L;

	public Queen(Board board, int xTile, int yTile, Player player) {
		super(board, Pieces.QUEEN, xTile, yTile, player, "Q");
	}


}
