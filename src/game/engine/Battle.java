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
	
	

	private static final int [][] PHASES_APPROACHING_TITANS = {
			{ 1, 1, 1, 2, 1, 3, 4 },
			{ 2, 2, 2, 1, 3, 3, 4 },
			{ 4, 4, 4, 4, 4, 4, 4 } 
	};
	private static final int WALL_BASE_HEALTH = 10000;
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;
	private final WeaponFactory weaponFactory;
	private final HashMap<Integer,TitanRegistry> titansArchives = DataLoader.readTitanRegistry();
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
		initializeLanes(initialNumOfLanes);
		this.battlePhase = BattlePhase.EARLY;

	}

	private void initializeLanes(int numOfLanes){

		Wall wall = new Wall(WALL_BASE_HEALTH);
		for(int i = 0; i < numOfLanes; i++){
			Lane lane = new Lane(wall);
			this.lanes.add(lane);
			this.originalLanes.add(lane);
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
	
	public HashMap<Integer, TitanRegistry> getTitansArchives() {
		return titansArchives;
	}
	
	public ArrayList<Lane> getOriginalLanes() {
		return originalLanes;
	}
	
	public ArrayList<Titan> getApproachingTitans() {
		return approachingTitans;
	}
	
	public int[][] getPHASES_APPROACHING_TITANS() {
		return PHASES_APPROACHING_TITANS;
	}

	public int getWALL_BASE_HEALTH() {
		return WALL_BASE_HEALTH;
	}

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}

	public PriorityQueue<Lane> getLanes() {
		return lanes;
	}

	public void refillApproachingTitans(){
		switch(battlePhase){
			case EARLY:
			for(int i = 0; i < 8; i++){	
				TitanRegistry titanData = titansArchives.get(PHASES_APPROACHING_TITANS[0][i]);
				switch(titanData.getCode()){
					case 1:
						approachingTitans.add(new PureTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 2:
						approachingTitans.add(new AbnormalTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 3:
						approachingTitans.add(new ArmoredTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 4:
						approachingTitans.add(new ColossalTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
				}
				
			}
			case INTENSE:
			for(int i = 0; i < 5; i++){	
				TitanRegistry titanData = titansArchives.get(PHASES_APPROACHING_TITANS[1][i]);
				switch(titanData.getCode()){
					case 1:
						approachingTitans.add(new PureTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 2:
						approachingTitans.add(new AbnormalTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 3:
						approachingTitans.add(new ArmoredTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 4:
						approachingTitans.add(new ColossalTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
				}
				
			}
			case GRUMBLING:
			for(int i = 0; i < 5; i++){	
				TitanRegistry titanData = titansArchives.get(PHASES_APPROACHING_TITANS[2][i]);
				switch(titanData.getCode()){
					case 1:
						approachingTitans.add(new PureTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 2:
						approachingTitans.add(new AbnormalTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 3:
						approachingTitans.add(new ArmoredTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
					case 4:
						approachingTitans.add(new ColossalTitan(titanData.getBaseHealth(), titanData.getBaseDamage(), titanData.getHeightInMeters(), 
						titanSpawnDistance, titanData.getSpeed(), titanData.getResourcesValue(), titanData.getDangerLevel()));
				}
				
			}
		}
	}
	

}
