package lab02;

public class MazeMain {

	public static void main(String[] args) {
		Maze maze = new Maze();
		switch(maze.respawn()) {
		default:
			maze.enterMaze();
			break;
		case ENTRANCE:
			maze.enterMaze();
		case TEA_CUPS:
			maze.aroundWeGo();
		case SPHINX:
			maze.sphnix();
		case PUZZLE_WALL:
			maze.puzzleWall();
		case CHAIN:
			maze.theChainLinkFence();
		case PASSWORD:
			maze.oakDoor();
		case BINARY_SEARCH:
			maze.potionProblem();
		case FINISH:
			maze.finish();
		}
	}
}
