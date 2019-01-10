/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.GenericHID;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class ControlElevator extends Command {

  private GenericHID controller;

  public ControlElevator(GenericHID controller) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);

    this.controller = controller;

  }

  public ControlElevator() {

    this(Robot.m_oi.getController());

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    // Reference POV Value
    int value = this.controller.getPOV();

    if ((value >= 0 && value < 90) || (value > 270 && value <= 360)) {
      // Up direction
      Robot.elevator.setSpeed(RobotMap.elevatorSpeed);
      SmartDashboard.putString("Elevator", "Up");
    } else if ((value > 90 && value < 270)) {
      // Down direction
      Robot.elevator.setSpeed(-RobotMap.elevatorSpeed);
      SmartDashboard.putString("Elevator", "Down");
    } else {
      // Stopped
      Robot.elevator.setSpeed(0);
      SmartDashboard.putString("Elevator", "Stop");
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.setSpeed(0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}
