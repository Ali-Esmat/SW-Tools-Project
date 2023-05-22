package com.project.repo;

import java.util.List;

import javax.persistence.TypedQuery;

public class RepoUtil {
    public static <T> T getFirstResultOrNull(TypedQuery<T> query) {
        List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }
}
