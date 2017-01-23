package org.usfirst.frc.team6134.robot;
import org.usfirst.frc.team6134.robot.commands.Dentro;
import org.usfirst.frc.team6134.robot.commands.Fuera;
import org.usfirst.frc.team6134.robot.commands.Paro;
import org.usfirst.frc.team6134.robot.commands.DentroRodillo;
import org.usfirst.frc.team6134.robot.commands.FueraRodillo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stick = new Joystick(0);
	public Joystick stick2 = new Joystick(1);
	Button B= new JoystickButton(stick, 2);
	Button Gatillo = new JoystickButton (stick2, 1 ),
			R = new JoystickButton (stick2, 3),
			Recogedor= new JoystickButton(stick2, 9),
			Disparo = new JoystickButton(stick2, 10);
	
			

	public OI(){
		 B.whileHeld( new Paro() );
		 Gatillo.whileHeld(new Fuera());
		 R.whileHeld(new Dentro());
		 Recogedor.whileHeld(new DentroRodillo());
		 Disparo.whileHeld(new FueraRodillo());
	}
}



