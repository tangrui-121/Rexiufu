package com.text.rexiufu.banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr'Tang on 2019/7/30.
 */

public class BannerBean {
    private String action;
    private String id;
    private String url;
    private String title;
    private String monitorParam;
    private String iscut_screen;
    private String share_url;
    private String ads_icon;
    private int img_resource;

    public int getImg_resource() {
        return img_resource;
    }

    public void setImg_resource(int img_resource) {
        this.img_resource = img_resource;
    }

    public static List<BannerBean> parseFromJson(JSONArray jsonArray){
        List<BannerBean> list = new ArrayList<>();
        for (int a = 0; a < jsonArray.length(); a++) {
            BannerBean app = new BannerBean();
            JSONObject jsonObj = null;
            try {
                jsonObj = jsonArray.getJSONObject(a);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            app.setAction(jsonObj.optString("action"));
            app.setId(jsonObj.optString("id"));
            app.setUrl(jsonObj.optString("url"));
            app.setTitle(jsonObj.optString("title"));
            app.setMonitorParam(jsonObj.optString("monitorParam"));
            app.setIscut_screen(jsonObj.optString("iscut_screen"));
            app.setShare_url(jsonObj.optString("share_url"));
            app.setAds_icon(jsonObj.optString("ads_icon"));
            list.add(app);
        }
        return list;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMonitorParam() {
        return monitorParam;
    }

    public void setMonitorParam(String monitorParam) {
        this.monitorParam = monitorParam;
    }

    public String getIscut_screen() {
        return iscut_screen;
    }

    public void setIscut_screen(String iscut_screen) {
        this.iscut_screen = iscut_screen;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getAds_icon() {
        return ads_icon;
    }

    public void setAds_icon(String ads_icon) {
        this.ads_icon = ads_icon;
    }
}
