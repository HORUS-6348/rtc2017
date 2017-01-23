package org.usfirst.frc.team6134.robot.subsystems;

import org.usfirst.frc.team6134.robot.Robot;
import org.usfirst.frc.team6134.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Garra extends Subsystem {
	Victor motor1= RobotMap.motor1;
	public void garra(){
	if(Robot.oi.stick2.getRawAxis(1)>0.25){
		motor1.set(1.0);
		}
	else if(Robot.oi.stick2.getRawAxis(1)<-0.25){
		motor1.set(-1.0);}
	else {
		motor1.set(0);
	}
	}
 public void parar(){
	 motor1.set(0);
 }

 public void initDefaultCommand() {
     // Set the default command for a subsystem here.
     //setDefaultCommand(new MySpecialCommand());
 }
}
