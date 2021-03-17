module org.gabysanchez {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.gabysanchez to javafx.fxml;
    exports org.gabysanchez;
}