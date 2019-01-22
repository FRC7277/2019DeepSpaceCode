package frc.robot.utils;

// Import GRIP pipeline
import frc.robot.utils.GripPipeline;
//Import the Main Robot
import frc.robot.Robot;

// Import CameraServer
import edu.wpi.first.cameraserver.CameraServer;

// Import OpenCV structs
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Rect;

// Import video stream objects
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

// Import dashboard interfaces
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Import ArrayList
import java.util.ArrayList;

public class VisionThread extends Thread {

    //Creating the double centerX in order to compute for the center of the object
    private double centerX = 0.0;
    @Override
    public void run() {

        // Setup camera chooser
        SendableChooser<String> vc_chooser = new SendableChooser<>();
        vc_chooser.setDefaultOption("Normal", "USB Camera 0");
        vc_chooser.addOption("Gray", "Gray");
        SmartDashboard.putData("Camera mode", vc_chooser);
        
        // Define contour output
        ArrayList<MatOfPoint> contourOuput;

        // Reference USB camera connected to RIO (and also start automatic capture)
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        // Define resolution for the camera
        camera.setResolution(320, 240);
        camera.setFPS(25);

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

        //Create pipeline object
        GripPipeline pipeline = new GripPipeline();
       

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
                //Passing the image mat into the GRIP pipeline
                pipeline.process(source);
                //Passing the output into the Array
                contourOuput = pipeline.filterContoursOutput();
                //Making a Seperate if statement checking for 
                //if there is something within the Contour Output
                if (!pipeline.filterContoursOutput().isEmpty()){
                Rect r = Imgproc.boundingRect(contourOuput.get(0));
                synchronized(Robot.imgLock){
                    //Computing for the X value center by getting the 
                    //X value in the conner and dividing it in two
                    centerX = r.x + (r.width / 2);
                }
                }
            }
            

        }

    }

    public double getCenterX(){
        return centerX;
    }
    
}