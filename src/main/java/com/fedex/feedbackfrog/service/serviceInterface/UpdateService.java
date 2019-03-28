package com.fedex.feedbackfrog.service.serviceInterface;

public interface UpdateService<T> {
  void editById(long id, T dto);
}
