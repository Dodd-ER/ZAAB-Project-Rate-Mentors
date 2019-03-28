package com.fedex.feedbackfrog.service.serviceInterface;

import java.util.List;

public interface ReadService<T> {
  List<T> getAll();
  T getById(long id);
  boolean existsById(long id);
}
