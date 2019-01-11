/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// Import Robot superclass
import edu.wpi.first.wpilibj.TimedRobot;

// Import command and schedular classes
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

// Import smartdashboard classes
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Import subsystems
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ElevatorLift;
import frc.robot.subsystems.HatchPlacer;

// Import camera stuff
import edu.wpi.first.cameraserver.CameraServer;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    // Create OI object
    public static OI m_oi = new OI();

    // Create subsystem objects
    public static DriveTrain m_drivetrain = new DriveTrain();
    public static ElevatorLift elevator = new ElevatorLift();
    public static HatchPlacer hatcher = new HatchPlacer();

    Command m_autonomousCommand;
    // Create dashboard choosers
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    SendableChooser<String> vc_chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {
        // Setup auto chooser
        //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
        // chooser.addOption("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", m_chooser);

        // Setup camera chooser
        vc_chooser.setDefaultOption("Normal", "USB Camera 0");
        vc_chooser.addOption("Gray", "Gray");
        SmartDashboard.putData("Camera mode", vc_chooser);

        // Put camera to dashboard
        // CameraServer.getInstance().startAutomaticCapture(0);

        // Create thread for processing camera (asynchrous)
        new Thread(() -> {

            // Reference USB camera connected to RIO (and also start automatic capture)
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            // Define resolution for the camera
            camera.setResolution(640, 480);
            camera.setFPS(30);

            // System.out.println("#&# Referenced camera "+camera);

            // Create a CvSink pulling from the camera
            CvSink cvSink = CameraServer.getInstance().getVideo();
            // Create cv output linked to SmartDashboard component 'Gray'
            CvSource outputStream = CameraServer.getInstance().putVideo("Gray", 640, 480);
            
            // Create misc output
            CvSource miscOutput = CameraServer.getInstance().putVideo("Misc", 640, 480);

            // Create mats (matrice capable of containing images) for source and output
            // Source stores a raw frame from cvSink
            Mat source = new Mat();
            // Output provides container to put processed frame in
            Mat output = new Mat();

            // Create choice string holder
            String choice;

            // While loop processes until the thread is interrupted
            while (!Thread.interrupted()) {
                // Frame is taken from cvSink and put into source
                if (cvSink.grabFrame(source) != 0) {
                    // Source is proccessed, and the result is put in output
                    // Current processing is turning to grayscale (mainly for testing)
                    Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
                    // Output is pushed into the output stream (linked to dashboard)
                    outputStream.putFrame(output);

                    //Ouput misc
                    choice = vc_chooser.getSelected();
                    if (choice.equals("Gray")) {
                        miscOutput.putFrame(output);
                    } else {
                        miscOutput.putFrame(source);
                    }
                }

            }

        }).start();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for
     * items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     *
     * <p>
     * This runs after the mode specific periodic functions, but before LiveWindow
     * and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
    }

    /**
     * This function is called once each time the robot enters Disabled mode. You
     * can use it to reset any subsystem information you want to clear when the
     * robot is disabled.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable chooser
     * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
     * remove all of the chooser code and uncomment the getString code to get the
     * auto name from the text box below the Gyro
     *
     * <p>
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons to
     * the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
         * switch(autoSelected) { case "My Auto": autonomousCommand = new
         * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
         * ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
