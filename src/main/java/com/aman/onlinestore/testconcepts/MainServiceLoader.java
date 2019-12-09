package com.aman.onlinestore.testconcepts;

import com.cognitree.onlinestore.ReportGenerator;
import com.cognitree.onlinestore.parser.PurchaseParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ServiceLoader;

public class MainServiceLoader {

    private static final Logger logger = LoggerFactory.getLogger(MainServiceLoader.class);

    public static void main(String[] args) throws IOException {
        MainServiceLoader main = new MainServiceLoader();
        main.transferPurchaseDetails();
    }

    private void transferPurchaseDetails() throws IOException {
        PurchaseParser parser = new PurchaseParser("D://TestDirectory//yoochoose-buys.dat");
        long start = System.currentTimeMillis();
        ServiceLoader<ReportGenerator> loader = ServiceLoader.load(ReportGenerator.class);
        parser.read().forEach(purchase -> loader.forEach(r->r.process(purchase)));
        loader.forEach(ReportGenerator::generateReport);
        long end = System.currentTimeMillis();
        logger.info("transferPurchaseDetails() : Time taken for execution: {}", end - start);
    }
}