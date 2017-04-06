
package org.usfirst.frc.team6348.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6348.robot.commands.AutonomoCentral;
import org.usfirst.frc.team6348.robot.commands.AutonomoLateralEngrane;
import org.usfirst.frc.team6348.robot.commands.AutonomoCentral;
import org.usfirst.frc.team6348.robot.commands.AutonomoLateralLinea;
import org.usfirst.frc.team6348.robot.subsystems.Escalador;
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


	Command autonomousCommand;
	UsbCamera camera;
	SendableChooser<Command> choose;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();
		trenMotriz = new TrenMotriz();
		escalador = new Escalador();
		oi = new OI();
		choose = new SendableChooser<Command>();

		choose.addDefault("Autónomo carril central", new AutonomoCentral());
		choose.addObject("Autónomo carriles laterales", new AutonomoLateralLinea());
		choose.addObject("Autónomo carriles lateral con engrane", new AutonomoLateralEngrane());
		
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putData(trenMotriz);
		SmartDashboard.putData(escalador);
		SmartDashboard.putData("Auto", choose);
		
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

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		trenMotriz.stop();
		escalador.stop();
	}

	@Override
	public void disabledPeriodic() {
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) choose.getSelected();
		autonomousCommand.start();
	}
	

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {	
		Scheduler.getInstance().run();
		
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
		
		trenMotriz.stop();
		trenMotriz.initDefaultCommand();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void robotPeriodic(){
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
}
