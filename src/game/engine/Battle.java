package game.engine;

import game.engine.weapons.factory.WeaponFactory;
import game.engine.titans.*;
import game.engine.lanes.Lane;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import java.util.HashMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Battle {
	
	private final int [][] PHASES_APPROACHING_TITANS;
	private final int WALL_BASE_HEALTH = 10000;
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;
	private WeaponFactory weaponFactory;
	private final HashMap<Integer,TitanRegistry> titanArchives = DataLoader.readTitanRegistry();
	private final ArrayList<Titan> approachingTitans;
	private final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes;
	
	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes, int initialResourcesPerLane) throws IOException{
		this.numberOfTitansPerTurn = 1;
		this.resourcesGathered = initialNumOfLanes * initialResourcesPerLane;
		this.numberOfTurns = numberOfTurns;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.weaponFactory = new WeaponFactory();
		this.originalLanes = new ArrayList<Lane>();
		this.approachingTitans = new ArrayList<Titan>();
		this.lanes = new PriorityQueue<Lane>();
		this.PHASES_APPROACHING_TITANS = new int[][] {} ;

	}

	private void initializeLanes(int numOfLanes){

		Wall wall = new Wall(WALL_BASE_HEALTH);

		for(int i = 0; i < numOfLanes; i++){
			lanes.add(new Lane(wall));
			originalLanes.add(new Lane(wall));

		}
	}

	public int getNumberOfTurns() {
		return numberOfTurns;
	}

	public void setNumberOfTurns(int numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}

	public int getResourcesGathered() {
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered) {
		this.resourcesGathered = resourcesGathered;
	}

	public BattlePhase getBattlePhase() {
		return battlePhase;
	}

	public void setBattlePhase(BattlePhase battlePhase) {
		this.battlePhase = battlePhase;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}

	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}

	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance) {
		this.titanSpawnDistance = titanSpawnDistance;
	}

	
	

}


