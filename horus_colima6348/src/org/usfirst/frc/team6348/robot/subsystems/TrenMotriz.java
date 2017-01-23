package org.usfirst.frc.team6348.robot.subsystems;

package org.usfirst.frc.team6348.robot.subsystems;

import org.usfirst.frc.team6348.robot.Robot;
import org.usfirst.frc.team6348.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;



public class TrenMotriz extends Subsystem {

	Spark motor_sup_izq = RobotMap.motor_sup_izq;
	Spark motor_sup_der = RobotMap.motor_sup_der;
	Spark motor_inf_izq = RobotMap.motor_inf_izq;
	Spark motor_inf_der = RobotMap.motor_inf_der;
	
	
	
	public void parar(){
		motor_sup_izq.set(0);
		motor_sup_der.set(0);
		motor_inf_izq.set(0);
		motor_inf_der.set(0);
	}
	
	public void initDefaultCommand(){
		
		
	}
}

