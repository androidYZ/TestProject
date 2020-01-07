package com.oneonezz.generic;


import java.io.Serializable;
import java.util.List;

public class AdvertTypeBean implements Serializable {
    /**
     * id : 1
     * type : 1
     * advert_type : 1
     * is_out : 1
     * is_usermsg : 1
     * number : 8
     * banner_data : [{"id":"1","advert_id":"1","banner_url":"https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d54b14f4a796.png","jump_url":"www.baidu.com"},{"id":"2","advert_id":"1","banner_url":"https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d54b14f4a796.png","jump_url":"www.baidu.com"}]
     * app_data : [{"id":"1","advert_id":"2","app_image_url":"https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d53751248e02.png","jump_url":"http://baidu.com","app_name":"111"},{"id":"2","advert_id":"2","app_image_url":"https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d53751248e02.png","jump_url":"http://baidu.com","app_name":"2222"}]
     * host_data : [{"id":"1","advert_id":"3","host_bg_url":"https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d54b14f4a796.png","host_title":"11111","host_username":"33","host_userportrait":"33","jump_url":"www.baidu.com"}]
     */

    private String id;
    private String type;
    private String advert_type;
    private String is_out;
    private String is_usermsg;
    private String number;
    private List<BannerDataBean> banner_data;
    private List<AppDataBean> app_data;
    private List<HostDataBean> host_data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdvert_type() {
        return advert_type;
    }

    public void setAdvert_type(String advert_type) {
        this.advert_type = advert_type;
    }

    public String getIs_out() {
        return is_out;
    }

    public void setIs_out(String is_out) {
        this.is_out = is_out;
    }

    public String getIs_usermsg() {
        return is_usermsg;
    }

    public void setIs_usermsg(String is_usermsg) {
        this.is_usermsg = is_usermsg;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<BannerDataBean> getBanner_data() {
        return banner_data;
    }

    public void setBanner_data(List<BannerDataBean> banner_data) {
        this.banner_data = banner_data;
    }

    public List<AppDataBean> getApp_data() {
        return app_data;
    }

    public void setApp_data(List<AppDataBean> app_data) {
        this.app_data = app_data;
    }

    public List<HostDataBean> getHost_data() {
        return host_data;
    }

    public void setHost_data(List<HostDataBean> host_data) {
        this.host_data = host_data;
    }

    public static class BannerDataBean implements Serializable {
        /**
         * id : 1
         * advert_id : 1
         * banner_url : https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d54b14f4a796.png
         * jump_url : www.baidu.com
         */

        private String id;
        private String advert_id;
        private String banner_url;
        private String jump_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdvert_id() {
            return advert_id;
        }

        public void setAdvert_id(String advert_id) {
            this.advert_id = advert_id;
        }

        public String getBanner_url() {
            return banner_url;
        }

        public void setBanner_url(String banner_url) {
            this.banner_url = banner_url;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }
    }

    public static class AppDataBean implements Serializable {
        /**
         * id : 1
         * advert_id : 2
         * app_image_url : https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d53751248e02.png
         * jump_url : http://baidu.com
         * app_name : 111
         */

        private String id;
        private String advert_id;
        private String app_image_url;
        private String jump_url;
        private String app_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdvert_id() {
            return advert_id;
        }

        public void setAdvert_id(String advert_id) {
            this.advert_id = advert_id;
        }

        public String getApp_image_url() {
            return app_image_url;
        }

        public void setApp_image_url(String app_image_url) {
            this.app_image_url = app_image_url;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }
    }

    public static class HostDataBean implements Serializable {
        /**
         * id : 1
         * advert_id : 3
         * host_bg_url : https://ttzdtest.oss-cn-beijing.aliyuncs.com/5d54b14f4a796.png
         * host_title : 11111
         * host_username : 33
         * host_userportrait : 33
         * jump_url : www.baidu.com
         */

        private String id;
        private String advert_id;
        private String host_bg_url;
        private String host_title;
        private String host_username;
        private String host_userportrait;
        private String jump_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdvert_id() {
            return advert_id;
        }

        public void setAdvert_id(String advert_id) {
            this.advert_id = advert_id;
        }

        public String getHost_bg_url() {
            return host_bg_url;
        }

        public void setHost_bg_url(String host_bg_url) {
            this.host_bg_url = host_bg_url;
        }

        public String getHost_title() {
            return host_title;
        }

        public void setHost_title(String host_title) {
            this.host_title = host_title;
        }

        public String getHost_username() {
            return host_username;
        }

        public void setHost_username(String host_username) {
            this.host_username = host_username;
        }

        public String getHost_userportrait() {
            return host_userportrait;
        }

        public void setHost_userportrait(String host_userportrait) {
            this.host_userportrait = host_userportrait;
        }

        public String getJump_url() {
            return jump_url;
        }

        public void setJump_url(String jump_url) {
            this.jump_url = jump_url;
        }
    }
}