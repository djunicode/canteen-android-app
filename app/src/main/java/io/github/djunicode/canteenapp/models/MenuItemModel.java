package io.github.djunicode.canteenapp.models;

public class MenuItemModel {

        private String title, time, price;



        public MenuItemModel(String title, String time, String price) {
            this.title = title;
            this.time = time;
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String name) {
            this.title = name;
        }

        public String getYear() {
            return price;
        }

        public void setYear(String year) {
            this.price = year;
        }

        public String getGenre() {
            return time;
        }

        public void setGenre(String time) {
            this.time = time;
        }




}
