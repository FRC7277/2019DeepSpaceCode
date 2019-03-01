/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.elevator.ControlElevator;

import frc.robot.RobotMap;

/**
 * Elevator subsystem.
 */
public class ElevatorLift extends Subsystem {
    // TODO Depreceated, replaced by ElevatorPID
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object for each motor
    private SpeedController motor;

    /**
     * Takes 2 port-integers, for left and right motor
     */
    public ElevatorLift(int motor) {
        
        // Create Motor objects using parameters
        this.motor = new Talon(motor);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public ElevatorLift() {
        
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

}
