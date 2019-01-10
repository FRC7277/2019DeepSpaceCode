/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import frc.robot.commands.ControlElevator;

import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Elevator subsystem.
 */
public class ElevatorLift extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object for each motor
    private WPI_VictorSPX left;
    private WPI_VictorSPX right;
    
    // Define speed controller group
    private SpeedControllerGroup main;
    
    /**
     * Takes 4 port integers, left-back, right-back, left-front, right-front
     * Takes a joystick object to pass to the default JoystickDrive command
     */
    public ElevatorLift(int left, int right) {
        
        // Create Motor objects using parameters
        this.left = new WPI_VictorSPX(left);
        this.right = new WPI_VictorSPX(right);
        
        // Create group of controllers
        this.main = new SpeedControllerGroup(this.left, this.right);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public ElevatorLift() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.leftElevator, RobotMap.rightElevator);

    }
    
    /**
     * Accessor for the motor group
     */
    public SpeedControllerGroup getMotors() {
        return this.main;
    }

    // Method to set motors speed
    /**
     * Sets the speed of the elevator lift motors
     * @param speed Value to set the motor speed to
     */
    public void setSpeed(double speed) {
        main.set(speed);
    }
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        
        setDefaultCommand(new ControlElevator(Robot.m_oi.getController()));
    }

}
