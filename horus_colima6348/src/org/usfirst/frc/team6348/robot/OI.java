package org.usfirst.frc.team6348.robot;

import edu.wpi.first.wpilibj.Joystick;
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
	
	Button B = new JoystickButton(stick0, 2); //Botón B para paro de emergencia
	
	public OI(){
		B.whileHeld(new ParoEmergencia()); //TODO: Implementar paro de emergencia
	}
	
	
	

}
