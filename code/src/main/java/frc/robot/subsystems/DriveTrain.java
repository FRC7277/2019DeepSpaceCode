/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    // Define motor object for each motor
    private WPI_TalonSRX leftBack;
    private WPI_TalonSRX rightBack;
    private WPI_TalonSRX leftFront;
    private WPI_TalonSRX rightFront;
    
    // Define left and right speedcontroller groups
    private SpeedControllerGroup left;
    private SpeedControllerGroup right;
    
    // Define differential drive
    private DifferentialDrive m_drive;
    
    /**
     * Takes 4 port integers, left-back, right-back, left-front, right-front
     */
    public DriveTrain(int lb, int rb, int lf, int rf) {
        
        // Create Motor objects using parameters
        this.leftBack = new WPI_TalonSRX(lb);
        this.rightBack = new WPI_TalonSRX(rb);
        this.leftFront = new WPI_TalonSRX(lf);
        this.rightFront = new WPI_TalonSRX(rf);
        
        // Create groups of controllers
        this.left = new SpeedControllerGroup(this.leftBack, this.leftFront);
        this.right = new SpeedControllerGroup(this.rightBack, this.rightFront);
        
        // Create diff drive
        this.m_drive = new DifferentialDrive(left, right);
        
    }
    
    /**
     * Constructs a drivetrain using RobotMap
     */
    public DriveTrain() {
        
        // Pass robotmap into DI constructor
        this(RobotMap.leftBack, RobotMap.rightBack, RobotMap.leftFront, RobotMap.rightFront);

    }
    
    /**
     * Accessor for the differential drive
     */
    public DifferentialDrive getDrive() {
      return this.m_drive;   
    }
    
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
