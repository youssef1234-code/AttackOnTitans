package game.engine.interfaces;

public interface Mobil {

	public int getDistance();

	public void setDistance(int distance);

	public int getSpeed();

	public void setSpeed(int speed);

	public default boolean hasReachedTarget() {
		return this.getDistance() <= 0;
	}

	public default boolean move() {
		this.setDistance(this.getDistance() - this.getSpeed());
		return this.hasReachedTarget();
	}
}
