package com.ramotion.foldingcell.examples.listview;

import android.view.View;

import java.util.ArrayList;

/**
 * Simple POJO model for example
 */
public class Item {

    private String id;
    private String name;
    private String description;
    private String airDay;
    private String network;
    private String banner;
    private String status;
    private String runtime;

    private View.OnClickListener requestBtnClickListener;

    public Item() {
    }

    public Item(String id, String name, String description, String airDay, String network, String banner, String status, String runtime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.airDay = airDay;
        this.network = network;
        this.banner = banner;
        this.status = status;
        this.runtime = runtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAirDay() {
        return airDay;
    }

    public void setAirDay(String airDay) {
        this.airDay = airDay;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public View.OnClickListener getRequestBtnClickListener() {
        return requestBtnClickListener;
    }

    public void setRequestBtnClickListener(View.OnClickListener requestBtnClickListener) {
        this.requestBtnClickListener = requestBtnClickListener;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null)
            return false;
        if (description != null ? !description.equals(item.description) : item.description != null)
            return false;
        if (airDay != null ? !airDay.equals(item.airDay) : item.airDay != null)
            return false;
        if (network != null ? !network.equals(item.network) : item.network != null)
            return false;
        if (banner != null ? !banner.equals(item.banner) : item.banner != null)
            return false;
        if (status != null ? !status.equals(item.status) : item.status != null)
            return false;
        return !(runtime != null ? !runtime.equals(item.runtime) : item.runtime != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (airDay != null ? airDay.hashCode() : 0);
        result = 31 * result + (network != null ? network.hashCode() : 0);
        result = 31 * result + (banner != null ? banner.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        return result;
    }

    /**
     * @return List of elements prepared for tests
     */
    public static ArrayList<Item> getTestingList() {
        ArrayList<Item> items = new ArrayList<>();
        return items;

    }

}
