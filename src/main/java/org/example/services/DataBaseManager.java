package org.example.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {
    private static DataBaseManager instance;
    private boolean databaseInitTables = false; // Deberíamos inicializar las tablas? Fichero de configuración
    private String databaseUrl = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; // Fichero de configuración se lee en el constructor
    private String databaseInitScript = "init.sql"; // Fichero de configuración se lee en el constructor
    private Connection conn;

    private DatabaseManager() {
        // Aquñi leeriamos el fichero de configuración.properties
        // Y estableceriamos la url de la base de datos y si hay que inicializar las tablas
        // Usamos Properties

        loadProperties();


    }

    private void loadProperties() {
        System.out.println("Cargando fichero de configuración de la base de datos");
        try {
            var file = ClassLoader.getSystemResource("database.properties").getFile();
            var props = new Properties();
            props.load(new FileReader(file));
            // Establecemos la url de la base de datos
            databaseUrl = props.getProperty("database.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
            databaseInitTables = Boolean.parseBoolean(props.getProperty("database.initTables", "false"));
            databaseInitScript = props.getProperty("database.initScript", "init.sql");
        } catch (IOException e) {
            System.out.println("Error al leer el fichero de configuración de la base de datos " + e.getMessage());
        }
    }
}

