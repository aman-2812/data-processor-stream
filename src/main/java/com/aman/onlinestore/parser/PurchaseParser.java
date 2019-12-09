package com.aman.onlinestore.parser;

import com.cognitree.onlinestore.model.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

public class PurchaseParser {

    private static final Logger logger = LoggerFactory.getLogger(PurchaseParser.class);

    private static final String SEPARATOR = ",";
    private final Path path;

    public PurchaseParser(String path) {
        this.path = Paths.get(path);
        logger.info("Reading from file {}", path);
    }

    private Date getTime(String timeStamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStamp);
        } catch (ParseException e) {
            logger.error("Exception Occurred : " + e);
        }
        return date;
    }

    public Stream<Purchase> read() throws IOException {
        return Files.lines(path)
                .map(lines -> lines.split(SEPARATOR))
                .map(params -> new Purchase(params[0], getTime(params[1]), params[2], Integer.parseInt(params[3]), Integer.parseInt(params[4])));
    }
}
