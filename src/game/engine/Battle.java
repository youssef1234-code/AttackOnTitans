package game.engine;

import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;
import game.engine.titans.*;
import game.engine.lanes.Lane;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.*;
import java.util.HashMap;
import java.util.Objects;
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
		if(battlePhase == BattlePhase.EARLY)
			for(int i = 0; i < PHASES_APPROACHING_TITANS[0].length; i++){	
				TitanRegistry titanData = titansArchives.get(PHASES_APPROACHING_TITANS[0][i]);
				approachingTitans.add(titanData.spawnTitan(titanSpawnDistance));
			}

		else if(battlePhase == BattlePhase.INTENSE)
			for(int i = 0; i < PHASES_APPROACHING_TITANS[1].length; i++){	
				TitanRegistry titanData = titansArchives.get(PHASES_APPROACHING_TITANS[1][i]);
				approachingTitans.add(titanData.spawnTitan(titanSpawnDistance));
			
		}
		else if(battlePhase == BattlePhase.GRUMBLING)
			for(int i = 0; i < PHASES_APPROACHING_TITANS[2].length; i++){	
				TitanRegistry titanData = titansArchives.get(PHASES_APPROACHING_TITANS[2][i]);
				approachingTitans.add(titanData.spawnTitan(titanSpawnDistance));
				
			}
		}
	
	public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException,
	InvalidLaneException{
		boolean flag = false;
		ArrayList<Lane> temp = new ArrayList<Lane>();
		Lane currentLane = null;
		while(!this.lanes.isEmpty()) {
			currentLane = lanes.poll();
			if(currentLane.getDangerLevel() == lane.getDangerLevel() &&
	                Objects.equals(currentLane.getLaneWall(), lane.getLaneWall()) &&
	                Objects.equals(currentLane.getTitans(),lane.getTitans()) &&
	                Objects.equals(currentLane.getWeapons(), lane.getWeapons())) {
				flag = true;
		}
			else {
				if(!currentLane.isLaneLost())
					temp.add(currentLane);
			}	
		}
		for(int i= 0 ;i<temp.size();i++) {
			this.lanes.add(temp.get(i));
		}
		
		if(lane.isLaneLost() || !flag)
			throw new InvalidLaneException();
		
		FactoryResponse response = weaponFactory.buyWeapon(resourcesGathered, weaponCode);
		lane.addWeapon(response.getWeapon());
		this.resourcesGathered = response.getRemainingResources();
		this.lanes.add(lane);
		performTurn();
	}

	private void addTurnTitansToLane(){
		Lane currLane = lanes.peek();
		for(int i = 0; i < numberOfTitansPerTurn; i++){
			if(approachingTitans.isEmpty())
				refillApproachingTitans();
			currLane.addTitan(approachingTitans.remove(0));
		}

	}

	private void moveTitans(){
		ArrayList<Lane> temp = new ArrayList<Lane>();
		while(!lanes.isEmpty()){
			lanes.peek().moveLaneTitans();
			temp.add(lanes.poll());
		}
		while(!temp.isEmpty()){
			lanes.add(temp.remove(0));
		}
		
	}

	//REDO 
	private int performWeaponsAttacks() {
        int res = 0;
        ArrayList<Lane> temp = new ArrayList<Lane>();
        Lane currentLane = null;
        while(!this.lanes.isEmpty()) {
            currentLane = this.lanes.poll();
            res += currentLane.performLaneWeaponsAttacks();
            temp.add(currentLane);
        }
        for(int i=0;i<temp.size();i++)
            this.lanes.add(temp.get(i));
        
        this.score += res;
        this.resourcesGathered += res;
        return res;
    }
	//REDO
    private int performTitansAttacks() {
        int res = 0;
        PriorityQueue<Lane> temp = new PriorityQueue<Lane>();
        Lane currentLane = null;
        while(!this.lanes.isEmpty()) {
            currentLane = this.lanes.poll();
            res += currentLane.performLaneTitansAttacks();
			if(!currentLane.isLaneLost())
            	temp.add(currentLane);
        }
        while(!temp.isEmpty())
            this.lanes.add(temp.poll());
        return res;
    }

    private void updateLanesDangerLevels() {
        ArrayList<Lane> temp = new ArrayList<Lane>();
        Lane currentLane = null;
        while(!this.lanes.isEmpty()) {
            currentLane = this.lanes.poll();
            currentLane.updateLaneDangerLevel();
            if(!currentLane.isLaneLost())
            	temp.add(currentLane);
        }
        for(int j=0;j<temp.size();j++)
            this.lanes.add(temp.get(j));
    }

    private void finalizeTurns() {
		numberOfTurns++; 
        if(numberOfTurns<15) {
            this.battlePhase = BattlePhase.EARLY;
            return;
        }
        if(numberOfTurns<30) {
            this.battlePhase = BattlePhase.INTENSE;
            return;
        }
        if(numberOfTurns>=30) {
            this.battlePhase = BattlePhase.GRUMBLING;
            if(numberOfTurns%5 == 0 && numberOfTurns != 30)
                numberOfTitansPerTurn*=2;
        }
		

    }
    
	public void passTurn(){
		performTurn();
	}
	private void performTurn(){
		moveTitans();
		performWeaponsAttacks();	
		performTitansAttacks();
		addTurnTitansToLane();
		updateLanesDangerLevels();
		finalizeTurns();
	}

	public boolean isGameOver(){
		return lanes.isEmpty();
	}



}
