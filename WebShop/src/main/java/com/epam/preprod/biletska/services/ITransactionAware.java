package com.epam.preprod.biletska.services;

import com.epam.preprod.biletska.transaction.TransactionDispatcher;

/**
 * The interface Transaction service.
 */
public interface ITransactionAware {

    /**
     * method for getting transaction dispatcher.
     *
     * @return transaction dispatcher
     */
    TransactionDispatcher getTransactionDispatcher();
}
