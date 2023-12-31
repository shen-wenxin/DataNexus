package com.szsc.customermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import com.szsc.customermanagement.config.AppConfig;

import java.nio.charset.Charset;

import javax.sql.DataSource;

@SpringBootApplication
@Import(AppConfig.class)
public class CustomerManagementApplication implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {

        // 设置默认字符编码为 UTF-8
        System.setProperty("file.encoding", "UTF-8");
        Charset.defaultCharset();
        
        SpringApplication.run(CustomerManagementApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        
        populator.setSqlScriptEncoding("UTF-8");
        populator.addScript(new ClassPathResource("create_table.sql"));
        DatabasePopulatorUtils.execute(populator, dataSource);
    }


}
