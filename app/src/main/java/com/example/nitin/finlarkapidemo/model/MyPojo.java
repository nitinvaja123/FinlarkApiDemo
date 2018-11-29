package com.example.nitin.finlarkapidemo.model;


import java.util.List;

public class MyPojo {
    private String status;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Event {
        private String is_sub_event;
        private String name;
        private String image;
        private String event_id;

        public String getIs_sub_event() {
            return is_sub_event;
        }

        public void setIs_sub_event(String is_sub_event) {
            this.is_sub_event = is_sub_event;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getEvent_id() {
            return event_id;
        }

        public void setEvent_id(String event_id) {
            this.event_id = event_id;
        }
    }

    public class Data {
        private List<Category> category;
        private List<Event> event;

        public List<Category> getCategory() {
            return category;
        }

        public void setCategory(List<Category> category) {
            this.category = category;
        }

        public List<Event> getEvent() {
            return event;
        }

        public void setEvent(List<Event> event) {
            this.event = event;
        }


    }

    public class Category {
        private String total_vendor;
        private String name;
        private String image;
        private String category_id;

        public String getTotal_vendor() {
            return total_vendor;
        }

        public void setTotal_vendor(String total_vendor) {
            this.total_vendor = total_vendor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }
    }
}
