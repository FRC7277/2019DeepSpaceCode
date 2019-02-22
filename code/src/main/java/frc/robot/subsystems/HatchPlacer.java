/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchPlacer extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Compressor compressor;
  private Solenoid solenoid;
  

  public HatchPlacer(int port) {
    compressor = new Compressor(7);
    solenoid = new Solenoid(7, 0);
    
    compressor.setClosedLoopControl(true);

  }

  public HatchPlacer() {

    this(RobotMap.solenoid);
  }

  public Solenoid getSolenoid() {
    return this.solenoid;
  }

  public void set(boolean state) {
    this.solenoid.set(state);
  }
  
  public boolean getPressureSwitchState(){
    return this.compressor.getPressureSwitchValue();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(command);
  }
}
