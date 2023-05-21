package com.project.repo;

import javax.persistence.TypedQuery;

public interface QueryFunction<T> {
    TypedQuery<T> execute();
}
