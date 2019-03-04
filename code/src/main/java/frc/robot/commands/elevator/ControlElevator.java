/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class ControlElevator extends BaseElevator {

  private XboxController controller;

  public ControlElevator(XboxController controller) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super();
    // TODO this subsystem uses elevatorEncoder, but only reads
    // because I dont want it being interrupted if the encoder is used by another
    // command (i.e. AlignElevator) it is not calling requires()
    // This may be something that should be fixed

    this.controller = controller;

  }

  public ControlElevator() {

    this(Robot.oi.getController());

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double vector = -this.controller.getY(GenericHID.Hand.kLeft) * RobotMap.elevatorModifier;

    //SmartDashboard.putNumber("Elevator", vector);
    SmartDashboard.putString("Elevator Speed", Double.toString(vector));

    SmartDashboard.putNumber("Elevator Distance", Robot.elevatorEncoder.getDistance());

    // Set spped of elevator
    Robot.elevator.setSpeed(vector);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  /*
  // Called once after isFinished returns true
  @Override
  protected void end() {
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    
  }
  */
}
