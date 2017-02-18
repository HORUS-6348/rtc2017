package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team6348.robot.commands.ExampleCommand;
import org.usfirst.frc.team6348.robot.commands.ParoEmergencia;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stick0 = new Joystick(0); //Joystick de Xbox 360
	public Joystick stick1 = new Joystick(1); //Joystick Logitech
	
	public Button B = new JoystickButton(stick0, 2); //Botón B para paro de emergencia
	
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
	
	
	public OI(){
		B.whenPressed(new ParoEmergencia());
	}
	
	
	

}
