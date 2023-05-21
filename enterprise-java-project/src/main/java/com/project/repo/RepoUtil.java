package com.project.repo;

import javax.persistence.TypedQuery;

public class RepoUtil {
    public static <T> T getFirstResultOrNull(TypedQuery<T> query) {
        if (query.getResultList().isEmpty()) {
            return null;
        }
        return query.getSingleResult();
    }
}
