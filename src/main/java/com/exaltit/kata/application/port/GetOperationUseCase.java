package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Operation;

import java.util.List;

public interface GetOperationUseCase {
    /** Method used to find all operations for given account id
     *
     * @param id
     * @return
     */
    List<Operation> findOperations(String id);

}
