package com.Icekiwi.Freeread.helpers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class Pager extends PageRequest {
  protected Pager(int page, int size, Sort sort) {
    super(page, size, sort);
    if (size > Constants.PAGE_MAX_LIMIT) {
      throw new IllegalArgumentException(
          "Max page limit size exceeded: " + Constants.PAGE_MAX_LIMIT);
    }
  }

  public static Pager of(Integer page, Integer size) {
    return of(page, size, Sort.unsorted());
  }

  public static Pager of(Integer page, Integer size, Sort sort) {
    return new Pager(
        page == null ? Constants.PAGE_DEFAULT_OFFSET : page,
        size == null ? Constants.PAGE_DEFAULT_LIMIT : size,
        sort);
  }
}
