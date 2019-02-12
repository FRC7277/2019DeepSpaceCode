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
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.Robot;
import frc.robot.RobotMap;

public class ControlElevator extends BaseElevator {

  private XboxController controller;

  private DigitalInput topGuard;
  private DigitalInput bottomGuard;

  public ControlElevator(XboxController controller, /*int bottomGuard,*/ DigitalInput topGuard) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super();

    this.controller = controller;

    //this.bottomGuard = getSwitches()[bottomGuard];
    this.topGuard = topGuard;

  }

  public ControlElevator() {

    this(Robot.m_oi.getController(), /*RobotMap.switches[0],*/ 
         Robot.elevator.getSwitches()[Robot.elevator.getSwitches().length - 1]);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    double vector = -this.controller.getY(GenericHID.Hand.kLeft);

    //SmartDashboard.putNumber("Elevator", vector);
    SmartDashboard.putString("Elevator", Double.toString(vector));

    boolean topState = topGuard.get();

    SmartDashboard.putString("Topguard", Boolean.toString(topState));

    // Cap speed when touching a switch guard
    if (topState) {
      vector = vector < 0 ? vector : 0.0;
    } /*else if (bottomGuard.get()) {
      vector = vector > 0 ? vector : 0.0;
    }
    */

    vector = vector + SmartDashboard.getNumber("ElPassive", RobotMap.elevatorPassive);
    SmartDashboard.putNumber("ELPOut", SmartDashboard.getNumber("ElPassive", RobotMap.elevatorPassive));
    vector = vector > 1 ? 1 : vector;

    SmartDashboard.putString("ModEl", Double.toString(vector));

    // Set spped of elevator
    Robot.elevator.setSpeed(vector);
    /* Old code to read DPad up down instead of joystick
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
    //*/
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
