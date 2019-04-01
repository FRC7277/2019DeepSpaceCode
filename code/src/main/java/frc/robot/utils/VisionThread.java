package frc.robot.utils;

// Import GRIP pipeline
import frc.robot.utils.GripPipeline;


// Import CameraServer
import edu.wpi.first.cameraserver.CameraServer;

// Import OpenCV structs
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.imgproc.Imgproc;


// Import video stream objects
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

// Import dashboard interfaces
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Import ArrayList
import java.util.ArrayList;

//Importing Test
import org.opencv.core.RotatedRect;
import org.opencv.core.Point;


public class VisionThread extends Thread {

    //Creating the double centerX in order to compute for the center of the object
    private double targetCenterX;
    private Mat source;
    private ArrayList<MatOfPoint> contourOutput;

    @Override
    public void run() {

        // Setup camera chooser
        SendableChooser<String> vc_chooser = new SendableChooser<>();
        vc_chooser.setDefaultOption("Normal", "USB Camera 0");
        vc_chooser.addOption("Gray", "Gray");
        SmartDashboard.putData("Camera mode", vc_chooser);

        // Reference USB camera connected to RIO (and also start automatic capture)
        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        // Define resolution for the camera
        camera.setResolution(320, 240);
        camera.setFPS(25);

        // System.out.println("#&# Referenced camera "+camera);

        // Create a CvSink pulling from the camera
        CvSink cvSink = CameraServer.getInstance().getVideo();
        // Create cv output linked to SmartDashboard component 'Gray'
        CvSource outputStream = CameraServer.getInstance().putVideo("Gray", 320, 240);
        // Create cv output linked to SmartDashboar d "contour" component
        CvSource contourStream = CameraServer.getInstance().putVideo("Contour", 320, 240);


        // Create misc output
        CvSource miscOutput = CameraServer.getInstance().putVideo("Misc", 320, 240);

        // Create mats (matrice capable of containing images) for source and output
        // Source stores a raw frame from cvSink
        this.source = new Mat();
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
                
                //Testing for Error
                SmartDashboard.putString("VisStage", "A");

                //Passing the image mat into the GRIP pipeline
                pipeline.process(source);

                //Testing for Error
                SmartDashboard.putString("VisStage", "B");

                //Passing the output into the Array
                this.contourOutput = pipeline.filterContoursOutput();
                
                //Testing for Error
                SmartDashboard.putString("VisStage", "F");

                //Checking for size of the outline
                int contourSize = pipeline.filterContoursOutput().size();
                SmartDashboard.putNumber("Contour count", contourSize);

                //contourStream.putFrame(contourOutptMat);
                
                //Making a Seperate if statement checking for 
                //if there is something within the Contour Output
                
                if (contourSize >= 2){

                    //Testing for Error
                    SmartDashboard.putString("VisStage", "C");
                    
                    //Grabbing the two countour
                    /*Rect r1 = Imgproc.boundingRect(contourOutput.get(0));
                    Rect r2 = Imgproc.boundingRect(contourOutput.get(1));*/
                    //Passing the contours into usable format
                    MatOfPoint2f firstBox = new MatOfPoint2f( contourOutput.get(0).toArray() );                
                    MatOfPoint2f secondBox = new MatOfPoint2f( contourOutput.get(1).toArray() );

                    Mat boxVisual = new Mat();
                    Imgproc.cvtColor(firstBox, boxVisual, Imgproc.COLOR_BGR2GRAY);
                    SmartDashboard.putNumber("Channels",boxVisual.channels());

                    // Output onto smartdashboard?
                    contourStream.putFrame(boxVisual/*pipeline.cvErodeOutput()*/);

                    //Creating a rotated rectangle that rotated at the right angle
                    RotatedRect outlineBox1 = Imgproc.minAreaRect(firstBox);
                    RotatedRect outlineBox2 = Imgproc.minAreaRect(secondBox);

                    Point center1 = outlineBox1.center;
                    Point center2 = outlineBox2.center;
                    /*.x returns the the top left corner of the bounding  
                    By adding the two top left corner and one rectangle worth of distance
                    we can get the center between the two reflective tape.
                    */
                    targetCenterX = ((center1.x + center2.x)/2 );
                    SmartDashboard.putNumber("Target Center X", targetCenterX);
                    SmartDashboard.putNumber("Angle Box 1", outlineBox1.angle);
                    SmartDashboard.putNumber("Angle Box 2", outlineBox2.angle);

                    //Testing for Error
                    SmartDashboard.putString("VisStage", "D");

                    
                    
                }else{
                    targetCenterX = -1;
                }

                //SmartDashboard.putString("VisStage", "E");

                
            }
            

        }

        SmartDashboard.putString("VisionThread Interrupted", "Yes");

    }

    public double getTargetCenterX(){
        return this.targetCenterX;
    }

    public Mat getSource() {
        return this.source;
    }

    public ArrayList<MatOfPoint> getContours() {
        return this.contourOutput;
    }
    
}