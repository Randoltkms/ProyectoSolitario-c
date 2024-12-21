package cibertec.edu.pe.demo.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

    @Value("${DB_LIBRERIA_URL}")
    private String dbLibreriaUrl;

    @Value("${DB_LIBRERIA_USER}")
    private String dbLibreriaUser;

    @Value("${DB_LIBRERIA_PASSWORD}")
    private String dbLibreriaPassword;

    @Value("${DB_LIBRERIA_DRIVER}")
    private String dbLibreriaDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();

        // Configuración de la conexión a la base de datos Librería
        config.setJdbcUrl(dbLibreriaUrl);
        config.setUsername(dbLibreriaUser);
        config.setPassword(dbLibreriaPassword);
        config.setDriverClassName(dbLibreriaDriver);

        // Configuración del pool de conexiones
        config.setMaximumPoolSize(15);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        System.out.println("###### HikariCP inicializado para la base de datos LIBRERIA ######");

        return new HikariDataSource(config);
    }
}
