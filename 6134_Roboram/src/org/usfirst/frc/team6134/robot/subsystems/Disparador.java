package org.usfirst.frc.team6134.robot.subsystems;

import org.usfirst.frc.team6134.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Disparador extends Subsystem {
	Victor motorm2 = RobotMap.motor2;
    public void atras(){
    	motorm2.set(1.0);
    }
    public void detener(){
    	motorm2.set(0);
    }
    public void disparar(){
    	motorm2.set(-1.0);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

