package org.usfirst.frc.team6134.robot.subsystems;

import org.usfirst.frc.team6134.robot.Robot;
import org.usfirst.frc.team6134.robot.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;



public class TrenMotriz extends Subsystem {


	Jaguar motorR = RobotMap.motorR;
	Victor motorR1 = RobotMap.motorR1;
	Jaguar motorL = RobotMap.motorL;
	Victor motorL1 = RobotMap.motorL1;
	double a=0.15, c=-1;
	double motorRValue, motorLValue;
	
	
	public void Paro(){
		motorR.set(0);
		motorL.set(0);
		motorR1.set(0);
		motorL1.set(0);
	}
	//Esta funcion hace 
	public void initDefaultCommand() {
		if(Robot.oi.stick.getRawButton(2)==true){
			motorR.set(0);
			motorL.set(0);
			motorR1.set(0);
			motorL1.set(0);

		} //Boton de panico'

		else{
			
			if(Robot.oi.stick.getRawButton(3)==true && c==1)c=-1;
			if(Robot.oi.stick.getRawButton(4)==true && c==-1)c=1;
			
			if(Robot.oi.stick.getRawAxis(1)<-a && Robot.oi.stick.getRawAxis(0)>a){
				motorR.set(c*(Robot.oi.stick.getRawAxis(1)));
				motorR1.set(c*(Robot.oi.stick.getRawAxis(1)));
				motorL.set(c*(-Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
				motorL1.set(c*(-Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
			}// hacia delante y girar hacia la izquierda

			else if(Robot.oi.stick.getRawAxis(1)<a && Robot.oi.stick.getRawAxis(0)<-a){
				motorR.set(c*(Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
				motorR1.set(c*(Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
				motorL.set(c*(-Robot.oi.stick.getRawAxis(1)));
				motorL1.set(c*(-Robot.oi.stick.getRawAxis(1)));
			}// hacia delante y girar hacia la derecha 

			else if(Robot.oi.stick.getRawAxis(1)>a && Robot.oi.stick.getRawAxis(0)>a){
				motorR.set(c*(Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
				motorR.set(c*(Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
				motorL.set(c*(-Robot.oi.stick.getRawAxis(1)));
				motorL1.set(c*(-Robot.oi.stick.getRawAxis(1)));
			}// hacia atras y a la izquierda

			else if(Robot.oi.stick.getRawAxis(1)>a && Robot.oi.stick.getRawAxis(0)<-a){
				motorR.set(c*(Robot.oi.stick.getRawAxis(1)));
				motorR1.set(c*(Robot.oi.stick.getRawAxis(1)));
				motorL.set(c*(-Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
				motorL1.set(c*(-Robot.oi.stick.getRawAxis(1)-Robot.oi.stick.getRawAxis(0/2)));
			}// hacia atras y a la derecha

			else{
				if(Math.abs(Robot.oi.stick.getRawAxis(1))>a){
					motorR.set(c*(Robot.oi.stick.getRawAxis(1)));
					motorR1.set(c*(Robot.oi.stick.getRawAxis(1)));
					motorL.set(c*(-Robot.oi.stick.getRawAxis(1))+ (Robot.oi.stick.getRawAxis(1)/6));
					motorL1.set(c*(-Robot.oi.stick.getRawAxis(1)) + (Robot.oi.stick.getRawAxis(1)/6));
				}//hacia adelante y atras

				else if(Math.abs(Robot.oi.stick.getRawAxis(4))>a){
					motorR.set((Robot.oi.stick.getRawAxis(4)));
					motorR1.set((Robot.oi.stick.getRawAxis(4)));
					motorL.set((Robot.oi.stick.getRawAxis(4)));
					motorL1.set((Robot.oi.stick.getRawAxis(4)));
				}//girar sobre su eje izq y derecha
				else{
					motorR.set(0);
					motorR1.set(0);
					motorL.set(0);
					motorL1.set(0);
				}
			}
		}
	}
}

