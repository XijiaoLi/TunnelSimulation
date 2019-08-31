package cs131.pa2.CarsTunnels;

import cs131.pa2.Abstract.Direction;
import cs131.pa2.Abstract.Tunnel;
import cs131.pa2.Abstract.Vehicle;

public class BasicTunnel extends Tunnel{
	
	private int numOfVihecles;
	private int numOfCars;
	private int numOfSleds;
	private Direction directn;

	public BasicTunnel(String name) {
		super(name);
		numOfVihecles = 0;
		numOfCars = 0;
		numOfSleds = 0;
	}

	@Override
	public synchronized boolean tryToEnterInner(Vehicle vehicle) {
		if (numOfVihecles == 0) {
			this.directn = vehicle.getDirection();
		}
		if (directn.equals(vehicle.getDirection())) {
			if (vehicle instanceof Car) {
				if (numOfSleds == 0 && numOfCars < 3) {
					numOfVihecles++;
					numOfCars++;
					return true;
				}
			} else if (vehicle instanceof Sled) {
				if (numOfSleds < 1 && numOfCars == 0) {
					numOfVihecles++;
					numOfSleds++;
					return true;
				}
			} 
		}
		return false;
	}

	@Override
	public synchronized void exitTunnelInner(Vehicle vehicle) {
		numOfVihecles--;
		if (vehicle instanceof Car) {
			numOfCars--;
		} else if (vehicle instanceof Sled) {
			numOfSleds--;
		} 
	}
	
}
