package com.text.rexiufu.mvvm;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableFloat;

public class ObservableUser {
    private ObservableField<String> name;

    private ObservableFloat price;

    private ObservableField<String> details;

    public ObservableUser(String name, float price, String details) {
        this.name = new ObservableField<>(name);
        this.price = new ObservableFloat(price);
        this.details = new ObservableField<>(details);
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableFloat getPrice() {
        return price;
    }

    public void setPrice(ObservableFloat price) {
        this.price = price;
    }

    public ObservableField<String> getDetails() {
        return details;
    }

    public void setDetails(ObservableField<String> details) {
        this.details = details;
    }
}
