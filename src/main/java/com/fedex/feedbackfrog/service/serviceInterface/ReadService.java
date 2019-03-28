package com.fedex.feedbackfrog.service.serviceInterface;

import java.util.List;

public interface ReadService<T> {
  List<T> getAll();
  List<T> getByTextContaining(String text);
  T getByName(String name);
  T getById(long id);
  boolean existsByName(String name);
  boolean existsByText(String text);
  boolean existsById(long id);
}
