package org.usfirst.frc.team6134.robot.subsystems;

import org.usfirst.frc.team6134.robot.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Rodillo extends Subsystem {
		Jaguar motor3= RobotMap.motor3;
		public void atras(){
			motor3.set(0.2);
		}
		public void detener(){
			motor3.set(0);
		}
		public void disparar(){
			motor3.set(-0.2);
		}
	 public void parar(){
		 motor3.set(0);
	 }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

