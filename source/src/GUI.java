import Airports.AirportsDB;
import Commands.InputParser;
import Parser.CSVParser;
import Reservations.ReservationsDB;
import TTARouteNetwork.FlightsDB;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Scanner;

public class GUI extends Application{

    private Button newbutton = new Button("New Window");
    private Button submitbutton = new Button("Submit");
    private TextArea input = new TextArea();
    private TextArea output = new TextArea();
    private Label requestLabel = new Label("Request: ");
    private Label connectionStatus = new Label("Connection Status: ");

    //The TextField where the user will type and enter their commands.
    private TextField requestTextField = new TextField();

    //The inputted request text
    private String requestText;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void helper(){
        String s = "---------------------------------------------------------------------\n" +
                "Input should follow one of the following formats:\n" +
                " (Anything in brackets are optional and all commands are ended with semi-colons)\n" +
                "Flight information request: info,origin,destination[,connections[,sort-order]]\n" +
                "Make reservation request: reserve,id,passenger\n" +
                "Retrieve reservations request: retrieve,passenger[,origin[,destination]]\n" +
                "Delete reservation request: delete,passenger,origin,destination\n" +
                "Airport information request: airport,airport\n" +
                "---------------------------------------------------------------------\n" +
                "Please input a command (Type HELP to see commands again, Type QUIT to exit)\n";
        input.setText(s);
        input.setWrapText(true);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {

        CSVParser csvp = new CSVParser();
        FlightsDB flights = csvp.getFlights();
        ReservationsDB reservations = csvp.getReservations();

        /* use airports if local airport info has been chosen (this is chosen by default)
         *
         * if FAA is chosen get each specific airport by calling:
         *
         * AirportFAAParse faaParse = new AirportFAAParse();
         * Airport airport = faaParse.getAirport(airportCode));
         * where airportCode is any three letter aiport code like "JFK,ORD,BOS"
         *
         */
        AirportsDB airports = csvp.getAirports();

        //if request text = 'quit' => execute csvp.writeToCSV();
        // (write reservations to reservationsDB)

        BorderPane b = new BorderPane();

        helper();

        //Treat the return key the same as pressing submit
        requestTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    requestText = requestTextField.getText();
                    connectAFRS();
                    requestTextField.setText("");

                }
            }
        });

        b.setCenter(buildCenter());

        //Event handler for the submit key, sends command to parser
        submitbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                requestText = requestTextField.getText();
                connectAFRS();
                requestTextField.setText("");

            }
        });

        b.setTop(buildTop());

        //Event handler to start a new instance of GUI
        newbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try {
                    GUI foo = new GUI();
                    foo.start(stage);
                }
                catch(Exception e){
                    
                }
            }
        });
        b.setBottom(buildBottom());
        Scene scene = new Scene(b, 800,450);
        primaryStage.setTitle("AFRS");
        primaryStage.setScene(scene);
        primaryStage.show();


        System.setOut(new PrintStream(System.out){
            @Override
            public void write(byte[] buf, int off, int len){
                super.write(buf,off,len);
                String msg = new String (buf,off,len);
                output.setText(output.getText() + msg);
            }
        });

    }


    private HBox buildBottom(){
        HBox hb = new HBox();
        VBox vb = new VBox();
        HBox.setHgrow(requestTextField,Priority.ALWAYS);
        hb.getChildren().addAll(requestLabel,requestTextField,submitbutton);
        return hb;
    }

    private HBox buildCenter(){
        HBox hb = new HBox();
        VBox vbRequest = new VBox();
        VBox vbResponse = new VBox();
        Label requestInfo = new Label("Request Information");
        Label responseInfo = new Label("Response");
        //hb.setSpacing(2);
        HBox.setHgrow(input, Priority.ALWAYS);
        HBox.setHgrow(output,Priority.ALWAYS);
        input.setEditable(false);
        output.setEditable(false);
        VBox.setVgrow(input,Priority.ALWAYS);
        VBox.setVgrow(output,Priority.ALWAYS);
        vbRequest.getChildren().addAll(requestInfo,input);
        vbResponse.getChildren().addAll(responseInfo,output);
        hb.getChildren().addAll(vbRequest,vbResponse);
        return hb;
    }

    private HBox buildTop(){
        HBox hb = new HBox();
        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);
        hb.getChildren().addAll(newbutton,region,connectionStatus);
        return hb;
    }

    private void connectAFRS(){
        CSVParser csvp = new CSVParser();
        csvp.createHashes();

        InputParser parser;

        if(requestText.trim().toLowerCase().contains("quit")){
            output.setText("Thank you for using AFRS!");

            //save any reservations upon quitting
            csvp.writeToCSV();
            //System.exit(0);
            return;
        }
        if(requestText.trim().toLowerCase().contains("help")){
            requestText = "";
        }
        if(requestText.trim().endsWith(";")){
            try{
                parser = new InputParser(requestText.substring(0, requestText.length() - 1));
                parser.executeRequest();
                requestText = "";
            }catch(Exception e){
                requestText = "";
                output.setText(e.getMessage());
            }
        }
    }
}
