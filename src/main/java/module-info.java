module com.balikicindebalik.steamclone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.balikicindebalik.steamclone to javafx.fxml;
    exports com.balikicindebalik.steamclone;
}