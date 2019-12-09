package com.aman.onlinestore;

import com.cognitree.onlinestore.parser.ClickParser;
import com.cognitree.onlinestore.parser.PurchaseParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private long processPurchaseTime = 0;
    private long processClickTime = 0;

    public static void main(String[] args) {
        Main main = new Main();
        try {
            long start = System.currentTimeMillis();
            List<ReportGenerator> purchaseReports;
            Registry registry = new Registry();
            purchaseReports = registry.getRegisteredReportGeneratorsPurchase();
            main.processPurchases(purchaseReports);
            List<ReportGenerator> clickReports;
            clickReports = registry.getRegisteredReportGeneratorClick();
            main.processClicks(clickReports);
            long end = System.currentTimeMillis();
            logger.info("main() : Total process time taken: {}",main.processPurchaseTime+main.processClickTime);
            logger.info("main() : Total time taken for execution of all queries : {}", end - start);
        } catch (IOException e) {
            logger.error("main() : Exception: " + e);
        }
    }

    private void processPurchases(List<ReportGenerator> purchaseReports) throws IOException {
        PurchaseParser parser = new PurchaseParser("D://TestDirectory//yoochoose-buys.dat");
        long start = System.currentTimeMillis();
        parser.read().forEach(purchase -> {
                    long startPurchaseProcess = System.currentTimeMillis();
                    purchaseReports.forEach(report -> report.process(purchase));
                    long endPurchaseProcess = System.currentTimeMillis();
                    processPurchaseTime += endPurchaseProcess - startPurchaseProcess;
                }
        );
        purchaseReports.forEach(ReportGenerator::generateReport);
        long end = System.currentTimeMillis();
        long totalTime = end - start;
        logger.info("processPurchases() : Time taken for execution of Purchase process : {}", processPurchaseTime);
        logger.info("processPurchases() : Time taken for execution of Purchase I/O : {}", totalTime - processPurchaseTime);
        logger.info("processPurchases() : Total time taken for execution of Purchase: {}", totalTime);
    }

    private void processClicks(List<ReportGenerator> clickReports) throws IOException {
        ClickParser parser2 = new ClickParser("D://TestDirectory//yoochoose-clicks.dat");
        long start = System.currentTimeMillis();
        parser2.read().forEach(click -> {
                    long startClickProcess = System.currentTimeMillis();
                    clickReports.forEach(report -> report.process(click));
                    long endClickProcess = System.currentTimeMillis();
                    processClickTime += endClickProcess - startClickProcess;
                }
        );
        logger.info("processClicks() : Generating Report");
        clickReports.forEach(ReportGenerator::generateReport);
        long end = System.currentTimeMillis();
        long totalClickTime = end - start;
        logger.info("processClicks() : Total time taken for execution of Click Process: {}", processClickTime);
        logger.info("processClicks() : Total time taken for execution of Click I/O: {}", totalClickTime - processClickTime);
        logger.info("processClicks() : Total time taken for execution of Click: {}", totalClickTime);
    }
}