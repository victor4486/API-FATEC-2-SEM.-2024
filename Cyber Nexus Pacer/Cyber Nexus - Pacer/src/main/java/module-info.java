module com.cyber.cybernexuspacer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    //opens com.cyber.cybernexuspacer to javafx.fxml;
    //exports com.cyber.cybernexuspacer;
    exports com.cyber.cybernexuspacer.controller;
    opens com.cyber.cybernexuspacer.controller to javafx.fxml;

}