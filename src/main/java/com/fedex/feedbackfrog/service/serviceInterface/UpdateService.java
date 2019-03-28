package com.fedex.feedbackfrog.service.serviceInterface;

public interface UpdateService<T> {
  void updateById(long id, T dto);
}
