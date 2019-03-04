/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.elevator.ControlElevator;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Elevator subsystem.
 */
public class ElevatorPID extends PIDSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object for each motor
    private SpeedController motor;

    /**
     * Takes 2 port-integers, for left and right motor
     */
    public ElevatorPID(int motor) {
        
        // Call super constructor with subsystem name and PID values
        // TODO static references to robotmap
        super("ElevatorPID", RobotMap.kP, RobotMap.kI, RobotMap.kD);

        // Configure PID system
        // Cap motor outputs to [-1, 1]
        setOutputRange(-RobotMap.maximumElevatorSpeed, RobotMap.maximumElevatorSpeed);
        // Reference tolerance from RobotMap TODO is this a static reference
        setAbsoluteTolerance(RobotMap.elevatorTolerance);
        // Set continuos to false (not exactly sure what this does, pulled from docs)
        getPIDController().setContinuous(false);

        // Create Motor objects using parameters
        this.motor = new Talon(motor);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public ElevatorPID() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.elevatorMotor);

    }

    /**
     * Accessor for the motor group
     */
    public SpeedController getMotor() {
        return this.motor;
    }

    // Method to set motors speed
    /**
     * Sets the speed of the elevator lift motors
     * @param speed Value to set the motor speed to
     */
    public void setSpeed(double speed) {

        // Add speed required to maintain position
        double vector = speed + SmartDashboard.getNumber("ElevatorPassive", RobotMap.elevatorPassive);

        // TODO add RobotMap maximums
        // Cap in (-1, 1) to prevent runtime error
        vector = vector > 1 ? 1 : vector;
        vector = vector < -1 ? -1 : vector;

        // Set speed of speed controllers (multiplied by modifiers)
        motor.set(vector);

    }
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        
        setDefaultCommand(new ControlElevator());
    }

    @Override
    protected double returnPIDInput() {
      // Return your input value for the PID loop
      // e.g. a sensor, like a potentiometer:
      // yourPot.getAverageVoltage() / kYourMaxVoltage;
      // TODO constant reference
      return Robot.elevatorEncoder.getDistance();
    }
  
    @Override
    protected void usePIDOutput(double output) {
      // Use output to drive your system, like a motor
      // e.g. yourMotor.set(output);
      this.setSpeed(output);
    }

}
