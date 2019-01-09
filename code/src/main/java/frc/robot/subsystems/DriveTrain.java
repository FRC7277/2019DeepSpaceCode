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
    WPI_TalonSRX leftBack = new WPI_TalonSRX(RobotMap.leftBack);
    WPI_TalonSRX rightBack = new WPI_TalonSRX(RobotMap.rightBack);
    WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.leftFront);
    WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.rightFront);
    SpeedControllerGroup left = new SpeedControllerGroup(leftBack, leftFront);
    SpeedControllerGroup right = new SpeedControllerGroup(rightBack, rightFront);
    private DifferentialDrive m_drive;

    public DriveTrain () {
      m_drive = new DifferentialDrive(left, right);

    }
    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}
