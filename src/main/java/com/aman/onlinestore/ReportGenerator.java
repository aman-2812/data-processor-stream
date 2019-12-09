package com.aman.onlinestore;

import com.cognitree.onlinestore.model.Click;
import com.cognitree.onlinestore.model.Purchase;

/**
 * Any class which needs to generate report related to clicks
 * for online project must implement this method.
 */
public interface ReportGenerator {
    /**
     * Receives immutable Purchase objects
     *
     * @param purchase
     */
    default void process(Purchase purchase){};

    /**
     * Receives an immutable click object
     * @param click
     */
    default void process(Click click){};

    /**
     * Generate report based on required query.
     * This method displays the report result and flushes
     * the data after everything is done.
     */
    default void generateReport(){}
}
