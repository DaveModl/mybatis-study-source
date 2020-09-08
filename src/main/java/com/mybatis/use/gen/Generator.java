package com.mybatis.use.gen;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Generator {
    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        //是否覆盖代码
        boolean overwrite = true;
        InputStream is = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser parser = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = parser.parseConfiguration(is);
        } catch (IOException | XMLParserException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        MyBatisGenerator generator = null;
        try {
            generator = new MyBatisGenerator(config,callback,warnings);
            generator.generate(null);
        } catch (InvalidConfigurationException | InterruptedException | IOException | SQLException e) {
            e.printStackTrace();
        }

        for (String warning : warnings){
            log.warn("{}",warning);
        }

    }
}
