module com.balikicindebalik.steamclone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.persistence;

    opens com.balikicindebalik.steamclone to javafx.fxml;
    exports com.balikicindebalik.steamclone;
}