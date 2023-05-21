package com.project.repo;

import javax.persistence.TypedQuery;

public class RepoUtil {
    public static <T> T getFirstResultOrNull(QueryFunction<T> function) {
        TypedQuery<T> query = function.execute();
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getSingleResult();
    }
}
