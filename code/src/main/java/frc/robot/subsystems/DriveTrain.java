/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

/**
 * Drive Base Subsystem.
 */
public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object for each motor
    private SpeedController leftBack;
    private SpeedController rightBack;
    private SpeedController leftFront;
    private SpeedController rightFront;
    
    // Define left and right speedcontroller groups
    private SpeedControllerGroup left;
    private SpeedControllerGroup right;
    
    // Define differential drive
    //private DifferentialDrive m_drive;
    private MecanumDrive drive;
    // Define joystick
    private Joystick joy;
    
    /**
     * Takes 4 port integers, left-back, right-back, left-front, right-front
     * Takes a joystick object to pass to the default JoystickDrive command
     */
    public DriveTrain(int lb, int rb, int lf, int rf, Joystick joy) {
        
        // Create Motor objects using parameters
        this.leftBack = new Talon(lb);
        this.rightBack = new Talon(rb);
        this.leftFront = new Talon(lf);
        this.rightFront = new Talon(rf);

        // Fix motor orientations
        this.rightBack.setInverted(true);
        this.leftFront.setInverted(true);
        
        // Create groups of controllers
        this.left = new SpeedControllerGroup(this.leftBack, this.leftFront);
        this.right = new SpeedControllerGroup(this.rightBack, this.rightFront);
        
        // Create drive
        //this.m_drive = new DifferentialDrive(left, right);
        this.drive = new MecanumDrive(leftFront, leftBack, rightFront, rightBack);

        // Reference joystick
        this.joy = joy;
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap and OI
     */
    public DriveTrain() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.leftBack, RobotMap.rightBack, RobotMap.leftFront, RobotMap.rightFront, Robot.oi.getJoystick());

    }
    
    /**
     * Accessor for the differential drive
     */
    
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        
        setDefaultCommand(new JoystickDrive(this.joy));
    }

    public MecanumDrive getDrive() {
        return this.drive;   
    }
  
    public void driveCartesian(Joystick joy){
        drive.driveCartesian(Robot.oi.scaleJoystickInput(-joy.getY()), 
                             Robot.oi.scaleJoystickInput(joy.getX()),
                             Robot.oi.scaleJoystickInput(joy.getZ()/*SmartDashboard.getNumber("ZMod", RobotMap.zMod)*/));
    }

    public void driveCartesian(double Y, double X, double Z){

        drive.driveCartesian(Y, X, Z);

    }

}
