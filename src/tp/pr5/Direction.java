package tp.pr5;

import tp.pr5.Direction;
import tp.pr5.Rotation;

/**
 * 
 * An enumerated type that represents the compass directions (north, east, south
 * and west) plus a value that represents an unknown direction.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public enum Direction {

	NORTH, SOUTH, EAST, WEST, UNKNOWN;

	/**
	 * Procedimiento que dada una dirección devuelve su opuesta
	 * 
	 * @param direction
	 * @return la dirección opuesta
	 */

	public Direction getOposite(Direction direction) {

		Direction ret = null;

		switch (direction) {

		case NORTH:
			ret = Direction.SOUTH;
			break;
		case EAST:
			ret = Direction.WEST;
			break;
		case SOUTH:
			ret = Direction.NORTH;
			break;
		case WEST:
			ret = Direction.EAST;
			break;
		default:
			ret = Direction.UNKNOWN;
			break;
		}

		return ret;
	}

	/**
	 * Procedimiento que dada una dirección y una rotación, calcula hacia donde
	 * mira el robot
	 * 
	 * @param dir
	 * @param rot
	 * @return dirección donde queda mirando el robot
	 */

	public Direction rotate(Direction dir, Rotation rot) {

		Direction ret = null;

		if (rot == Rotation.RIGHT) {
			if (dir == Direction.NORTH)
				ret = Direction.EAST;
			else if (dir == Direction.EAST)
				ret = Direction.SOUTH;
			else if (dir == Direction.SOUTH)
				ret = Direction.WEST;
			else if (dir == Direction.WEST)
				ret = Direction.NORTH;

		} else if (rot == Rotation.LEFT) {
			if (dir == Direction.NORTH)
				ret = Direction.WEST;
			else if (dir == Direction.EAST)
				ret = Direction.NORTH;
			else if (dir == Direction.SOUTH)
				ret = Direction.EAST;
			else if (dir == Direction.WEST)
				ret = Direction.SOUTH;

		} else
			ret = Direction.UNKNOWN;

		return ret;

	}

}
