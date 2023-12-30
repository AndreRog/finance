package com.andrerog.finance;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.jboss.logging.Logger;

/**
 * Hello world!
 *
 */
@QuarkusMain
public class App {

    private static final Logger LOG = Logger.getLogger(App.class);

    public static void main(String ... args) {
        LOG.info("Audit logs Started when application started");
        Quarkus.run(args);
    }
}