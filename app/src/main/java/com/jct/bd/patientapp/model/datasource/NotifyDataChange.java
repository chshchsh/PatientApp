package com.jct.bd.patientapp.model.datasource;

public interface NotifyDataChange<T> {
    void OnDataChanged(T obj);

    void onFailure(Exception exception);
}
