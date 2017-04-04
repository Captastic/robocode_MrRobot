package captastic;
import robocode.*;
import java.awt.Color;
import robocode.WinEvent;
import static robocode.util.Utils.*;
import robocode.util.Utils;

/**
 * MrsRobot - a robot by (Anthony)
 */
public class MrsRobot extends AdvancedRobot {

private int moveDirection = 1;
private int turnCount = 0;
private double gunTurnAmount;

////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void run() {
			setAdjustGunForRobotTurn(true);
			setColors(Color.black,Color.red,Color.red);
			
				
		while(true) {
		execute();
		setTurnGunRight(10);		
		turnCount++;
						
		if (turnCount > 2) {gunTurnAmount = -20;}
		if (turnCount > 5 ) {gunTurnAmount = 20;}
		if (turnCount == 10) { turnCount = 0;}
		
		}
	
}	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void onScannedRobot(ScannedRobotEvent e) {
		double distance = e.getDistance();
		
				
		if (e.getDistance() > 150) {
			gunTurnAmount = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
			setTurnGunRight(gunTurnAmount); 
			setTurnRight(e.getBearing() + 90 - (15 * moveDirection));
			setAhead((e.getDistance() * moveDirection));
			fire(2);
			} 
			else {
			gunTurnAmount = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
			setTurnGunRight(gunTurnAmount);
			setTurnRight(e.getBearing() + 90 - (15 * moveDirection));
			setAhead(e.getDistance() * moveDirection);
			fire(3);
			}	
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void onHitByBullet(HitByBulletEvent e) {
		moveDirection = -moveDirection;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void onCustomEvent(CustomEvent e){}	
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void onHitWall(HitWallEvent e) {
		moveDirection = -moveDirection;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void onWin(WinEvent e){
	//victory fanfare
		for (int i = 0; i < 50; i++){
			turnGunRight(30);
			turnGunRight(30);
		}
	}
}		