package com.fighter_lee.filepicker.inter;

import java.util.List;

public interface FileResultCallback<T> {
    void onResultCallback(List<T> files);
  }