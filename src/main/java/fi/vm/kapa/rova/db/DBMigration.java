package fi.vm.kapa.rova.db;

import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

/**
 * Created by jkorkala on 13/03/2017.
 */
public interface
DBMigration {

    String INFO = "/rest/db/info";
    String MIGRATE = "/rest/db/migrate/{dataset}";

    public String info();

    public ResponseEntity<String> migrate(String noValidate, String dataset) throws URISyntaxException ;
}
