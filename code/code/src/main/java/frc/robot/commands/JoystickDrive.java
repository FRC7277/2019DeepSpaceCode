/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class JoystickDrive extends Command {
  
  Joystick joy;
  
  public JoystickDrive(Joystick joy) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_drivetrain);
    
    // Reference passed joystick
    this.joy = joy;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Robot.m_drivetrain.arcadeDrive(this.joy);

    SmartDashboard.putString("Drive X:", Double.toString(this.joy.getX()));
    SmartDashboard.putString("Drive Z:", Double.toString(this.joy.getZ()));
    SmartDashboard.putString("Drive Y:", Double.toString(this.joy.getY()));
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_drivetrain.getDrive().arcadeDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
