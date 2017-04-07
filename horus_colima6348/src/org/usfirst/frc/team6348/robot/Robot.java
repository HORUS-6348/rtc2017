
package org.usfirst.frc.team6348.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.commands.AutonomoCentral;
import org.usfirst.frc.team6348.robot.commands.AutonomoLateralEngrane;
import org.usfirst.frc.team6348.robot.commands.AutonomoCentral;
import org.usfirst.frc.team6348.robot.commands.AutonomoLateralLinea;
import org.usfirst.frc.team6348.robot.subsystems.Escalador;
import org.usfirst.frc.team6348.robot.subsystems.IluminadorLED;
import org.usfirst.frc.team6348.robot.subsystems.TrenMotriz;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static TrenMotriz trenMotriz;
	public static Escalador escalador;
	public static OI oi;
	public static IluminadorLED iluminadorLED;

	Command autonomousCommand;
	UsbCamera camera;


	@Override
	public void robotInit() {
		RobotMap.init();
		
		trenMotriz     = new TrenMotriz();
		escalador      = new Escalador();
		iluminadorLED  = new IluminadorLED();
		oi             = new OI();

		setupGyro();
		setupCamera(640, 480, 24);
	}

	private void setupGyro() {
		oi.gyro.calibrate();
	}
	
	private void setupCamera(int width, int height, int fps){
		camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, width, height, fps);
		
	}

	@Override
	public void disabledInit() {
		trenMotriz.stop();
		escalador.stop();
		iluminadorLED.stop();
	}


	@Override
	public void autonomousInit() {

	}
	
	@Override
	public void autonomousPeriodic() {	
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		
		trenMotriz.stop();
		trenMotriz.initDefaultCommand();
	}


	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void robotPeriodic() {
		System.out.println("oie cy");
	}

}
