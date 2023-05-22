package com.project.service.util;

public interface EntityToResponseFunction<T, E> {
    T execute(E entity);
}
